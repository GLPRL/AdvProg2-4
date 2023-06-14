package com.example.advprog2_4;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
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
    NotificationManager notificationManager;
    int importance = NotificationManager.IMPORTANCE_DEFAULT;
    NotificationManagerCompat notificationManagerCompat;


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

        createNotificationChannel();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        //TODO: Listen, and call to onNewMessage with notificationManagerCompat + channelID as argument


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
                                NotificationChannel channel = new NotificationChannel(channelID, name, importance);
                                channel.setDescription(desc);

                                channel = new NotificationChannel(channelID, name, importance);
                                notificationManager.createNotificationChannel(channel);
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
            String newToken = task.getResult();
        });
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

    /**
     * Create new channels when first logging in to the application
     */
    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for (int i = 0; i < contactList.size(); i++) {
                CharSequence name = getString(R.string.channel_name);
                String desc = getString(R.string.channel_name);
                String channelID = String.valueOf(i);
                NotificationChannel channel = new NotificationChannel(channelID, name, importance);
                channel.setDescription(desc);

                notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    /**
     * Build and notify the user of a new message.
     */
    public void onNewMessage(int channelID) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, String.valueOf(channelID))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("ChatApp")
                .setContentText("Message received from " + contactList.get(channelID).getDisplayname())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(channelID, builder.build());
    }
}
