package com.example.advprog2_4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        RecyclerView recyclerView = findViewById(R.id.recyclerContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactsAdapter(getApplicationContext(),generateContactList()));

    }
    public List<Contact> generateContactList(){
        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(new Contact("Dekel","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Naor","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Gal","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Dekel","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Naor","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Gal","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Dekel","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Naor","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Gal","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Dekel","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Naor","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Gal","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Dekel","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Naor","10/10/1999",R.drawable.baseline_account_circle_24));
        contactList.add(new Contact("Gal","10/10/1999",R.drawable.baseline_account_circle_24));
        return contactList;
    }
}
