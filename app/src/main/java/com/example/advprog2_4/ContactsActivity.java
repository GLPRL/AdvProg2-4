package com.example.advprog2_4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.advprog2_4.api.ChatAPI;
import com.example.advprog2_4.viewmodels.ChatsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;


public class ContactsActivity extends AppCompatActivity {
    AppDB db;
    ContactsAdapter contactsAdapter;

    private ChatsViewModel chatsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB")
                .allowMainThreadQueries()
                .build();


        Global.getInstance().setChatDao(db.ChatDao());

        chatsViewModel = new ViewModelProvider(this).get(ChatsViewModel.class);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        de.hdodenhof.circleimageview.CircleImageView profilePic = findViewById(R.id.profilePicView);
        TextView tvLoggedUser = findViewById(R.id.loggedUser);
        tvLoggedUser.setText(Global.getInstance().getUserDisplayName());
        CircleImageView profilePicView = findViewById(R.id.profilePicView);
        profilePicView.setImageResource(R.drawable.profile_pic_2);
        profilePic.setImageBitmap(Global.getInstance().getUserProfilePic());

        RecyclerView recyclerView = findViewById(R.id.recyclerContacts);
        chatsViewModel.getChats().observe(this, chats -> {
            recyclerView.setAdapter(new ContactsAdapter(ContactsActivity.this, chats));
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //contactsAdapter = new ContactsAdapter(ContactsActivity.this, Global.getInstance().getChatDao().index());
        //recyclerView.setAdapter(contactsAdapter);

        FloatingActionButton btnLogout = findViewById(R.id.btnLogout);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, MainActivity.class);
                db.clearAllTables();
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
                        //String username = input.getText().toString().trim();
                        if (!username.isEmpty()) {
                            ChatAPI api = new ChatAPI();
                            api.postChat(username);
                            //contactsAdapter = new ContactsAdapter(ContactsActivity.this,
                            //        Global.getInstance().getChatDao().index());
                            //recyclerView.setAdapter(contactsAdapter);
                            //Create new channel on adding new contact.
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                CharSequence name = "ChatApp";
                                String desc = "New message from " + username;
                                //String channelID = String.valueOf(Global.getInstance().getChatDao()
                                //                                                        .index().size() - 1);

                            }
                            //recyclerView.getAdapter().notifyDataSetChanged();
                            //recreate();

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
        //FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
        //    FBToken = task.getResult();
        //});

        //Each topic is a chatID
        //for (int i = 0; i < contactList.size(); i++) {
        //    FirebaseMessaging.getInstance().subscribeToTopic(contactList.get(i).getChatID());
        //}
    }

    protected void onDestroy() {
        super.onDestroy();
        db.clearAllTables();
    }
}
