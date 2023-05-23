package com.example.chattingapp.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.chattingapp.ContactModel;
import com.example.chattingapp.R;
import com.example.chattingapp.RecyclerContactAdapter;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {
ArrayList<ContactModel> arrayContacts=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        RecyclerView contactRecycler=findViewById(R.id.contactRecycler);

        contactRecycler.setLayoutManager(new LinearLayoutManager(this));

        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Milan"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Matru"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Chirag"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Anil"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Pradyumna"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Gourav"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Mahaveer"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Tuin"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Madhusudan"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Sakil"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Sakil"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Sakil"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Sakil"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Sakil"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Sakil"));
        arrayContacts.add(new ContactModel(R.drawable.prrofile,"Sakil"));


        RecyclerContactAdapter adapter=new RecyclerContactAdapter(this, arrayContacts, new RecyclerContactAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ContactModel contactModel) {
                Intent i=new Intent(ContactList.this, ChatScreen.class );
                i.putExtra("userName",arrayContacts.getClass());
                i.putExtra("profilePic",arrayContacts.getClass());
                startActivity(i);

            }
        });
        contactRecycler.setAdapter(adapter);


    }
}