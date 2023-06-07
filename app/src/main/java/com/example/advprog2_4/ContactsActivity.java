package com.example.advprog2_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        TextView tvLoggedUser = findViewById(R.id.loggedUser);
        tvLoggedUser.setText(username);
        CircleImageView profilePicView = findViewById(R.id.profilePicView);
        profilePicView.setImageResource(R.drawable.profile_pic_2);
        RecyclerView recyclerView = findViewById(R.id.recyclerContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactsAdapter(ContactsActivity.this,generateContactList()));


    }
    public List<Contact> generateContactList(){
        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));

        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Test","10/10/1999",R.drawable.profile_pic_1));


        return contactList;
    }
}
