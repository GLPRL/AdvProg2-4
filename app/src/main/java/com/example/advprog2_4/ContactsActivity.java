package com.example.advprog2_4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advprog2_4.objects.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsActivity extends AppCompatActivity {
    List<Contact> contactList;
    static String FBToken;

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
        contactList = generateContactList();
        recyclerView.setAdapter(new ContactsAdapter(ContactsActivity.this, contactList));
        FloatingActionButton btnLogout = findViewById(R.id.btnLogout);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactsActivity.this);
                builder.setTitle("Add Contact");
                final EditText input = new EditText(ContactsActivity.this);
                builder.setView(input);
                input.setHint("Type username...");
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = input.getText().toString().trim();
                        if (!username.isEmpty()) {
                            Contact newContact = new Contact(0, username, R.drawable.profile_pic_1);
                            contactList.add(newContact);
                            recyclerView.getAdapter().notifyDataSetChanged();
                            //Create new channel on adding new contact.
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                CharSequence name = "ChatApp";
                                String desc = "New message from " + username;
                                String channelID = String.valueOf(contactList.size() - 1);
                            }

                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }

        });
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            FBToken = task.getResult();
        });
    }

    public static String getFBToken() {
        return FBToken;
    }
    public List<Contact> generateContactList() {
        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(new Contact(0, "Test", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Dekel", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Hemi", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Test", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Dekel", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Hemi", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Test", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Dekel", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Hemi", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Test", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Dekel", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Hemi", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Test", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Dekel", R.drawable.profile_pic_1));
        contactList.add(new Contact(0, "Hemi", R.drawable.profile_pic_1));
        return contactList;
    }

}
