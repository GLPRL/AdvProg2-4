package com.example.advprog2_4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        CircleImageView profilePicView = findViewById(R.id.profilePicView);
        profilePicView.setImageResource(R.drawable.profile_pic_2);
        RecyclerView recyclerView = findViewById(R.id.recyclerContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactsAdapter(getApplicationContext(),generateContactList()));

    }
    public List<Contact> generateContactList(){
        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(new Contact("Dekel","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Naor","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Gal","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Dekel","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Naor","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Gal","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Dekel","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Naor","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Gal","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Dekel","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Naor","10/10/1999",R.drawable.profile_pic_1));
        contactList.add(new Contact("Gal","10/10/1999",R.drawable.profile_pic_1));

        return contactList;
    }
}
