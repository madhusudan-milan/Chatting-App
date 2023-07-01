package com.example.chattingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chattingapp.Model.ContactModel;
import com.example.chattingapp.R;
import com.example.chattingapp.dashboard.ChatScreen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    ArrayList<ContactModel> arrContacts;
    Context context;

    public UserAdapter(ArrayList<ContactModel> arrContacts, Context context) {
        this.arrContacts = arrContacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel contactModel =arrContacts.get(position);
        Picasso.get().load(contactModel.getImg()).placeholder(R.drawable.avatar).into(holder.image);
        holder.userName.setText(contactModel.getUserName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatScreen.class);
                intent.putExtra("userId",contactModel.getUserId());
                intent.putExtra("profilePic",contactModel.getImg());
                intent.putExtra("userName",contactModel.getUserName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView userName,lastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image =itemView.findViewById(R.id.contact_img);
            userName=itemView.findViewById(R.id.contact_name);
            lastMessage=itemView.findViewById(R.id.userMessage);
        }
    }
}
