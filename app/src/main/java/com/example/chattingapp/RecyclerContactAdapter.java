package com.example.chattingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chattingapp.dashboard.ChatScreen;
import com.example.chattingapp.dashboard.ContactList;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel> arrContacts;
    public RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrContacts)
    {
        this.context=context;
        this.arrContacts =arrContacts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.contactimg.setImageResource(arrContacts.get(position).img);
        holder.textName.setText(arrContacts.get(position).name);
        //this clicklistner will be redirect the contact name to the chat screen
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatScreen.class );
                intent.putExtra("userNmae",arrContacts.getClass());
                intent.putExtra("profilePic",arrContacts.getClass());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView textName;

        ImageView contactimg;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textName=itemView.findViewById(R.id.contact_name);
            contactimg=itemView.findViewById(R.id.contact_img);


        }
    }
}
