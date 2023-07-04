package com.example.chattingapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chattingapp.Model.MessageModel;
import com.example.chattingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ChatAdapter extends RecyclerView.Adapter {
    ArrayList<MessageModel> messageModels;
    Context context;
    int SENDER_VIEW_TYPE=1;
    int RECIVER_VIEW_TYPE=2;
    String reciverId;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context, String reciverId) {
        this.messageModels = messageModels;
        this.context = context;
        this.reciverId = reciverId;
    }

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType==SENDER_VIEW_TYPE)
       {
           View view =LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
           return new SenderViewHolder(view);
       }
       else {
           View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver,parent,false);
           return new ReciverViewHolder(view);
       }
    }

    @Override
    public int getItemViewType(int position) {

        if (Objects.equals(messageModels.get(position).getuId(), FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else {
            return RECIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel=messageModels.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String senderRoom = FirebaseAuth.getInstance().getUid()+reciverId;
                                database.getReference().child("chats").child(senderRoom)
                                        .child(messageModel.getMessageId())
                                        .setValue(null);

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();


                return false;
            }
        });





        if (holder.getClass() == SenderViewHolder.class)
        {
            ((SenderViewHolder)holder).senderMessage.setText(messageModel.getMessage());
            Date date=new Date(messageModel.getTimeStamp());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm:a");
            String strDate=simpleDateFormat.format(date);
            ((SenderViewHolder)holder).senderTime.setText(strDate.toString());
        }
        else {
            ((ReciverViewHolder)holder).reciverMessage.setText(messageModel.getMessage());
            Date date=new Date(messageModel.getTimeStamp());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm:a");
            String strDate=simpleDateFormat.format(date);
            ((ReciverViewHolder)holder).reciverTime.setText(strDate.toString());
        }
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public  class  ReciverViewHolder extends RecyclerView.ViewHolder{
        TextView reciverMessage,reciverTime;

        public ReciverViewHolder(@NonNull View itemView) {
            super(itemView);
            reciverMessage =itemView.findViewById(R.id.reciverText);
            reciverTime =itemView.findViewById(R.id.reciverTime);

        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView senderMessage,senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMessage =itemView.findViewById(R.id.senderText);
            senderTime =itemView.findViewById(R.id.senderTime);
        }
    }
}
