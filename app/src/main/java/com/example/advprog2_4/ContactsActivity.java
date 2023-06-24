package com.example.advprog2_4;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.advprog2_4.api.ChatAPI;
import com.example.advprog2_4.api.FirebaseTokenAPI;
import com.example.advprog2_4.viewmodels.ChatsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

import java.net.URISyntaxException;

import de.hdodenhof.circleimageview.CircleImageView;
import io.socket.emitter.Emitter;
import io.socket.client.IO;


public class ContactsActivity extends AppCompatActivity {
    AppDB db;
    ContactsAdapter contactsAdapter;
    private ChatsViewModel chatsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{POST_NOTIFICATIONS}, 1);
            }
        }
        if (db != null) {
            if (db.isOpen()) {
                new Thread(() -> db.clearAllTables());
            }
        }
        Runnable start = new Runnable() {
            @Override
            public void run() {
                db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ContactsDB").allowMainThreadQueries().build();
                Global.getInstance().setChatDao(db.ChatDao());
            }
        };
        Thread t = new Thread(start);
        t.start();
        try {
            t.join();
            chatsViewModel = new ViewModelProvider(this).get(ChatsViewModel.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
        recyclerView.post(() -> {
            if (recyclerView.getAdapter() == null) {
                //do nothing
            } else {
                int count = recyclerView.getAdapter().getItemCount();
                if (count != 0) {
                    recyclerView.scrollToPosition(count - 1);
                }
            }
        });

        try {
            Global.getInstance().setSocket(IO.socket(Global.getInstance().getServerAddress()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Global.getInstance().getSocket().connect();
        Global.getInstance().getSocket().on("hello", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Looper.prepare();
            }
        });
        Global.getInstance().getSocket().on("receiveMessage", args -> {
            Looper.prepare();
        });
        FloatingActionButton btnLogout = findViewById(R.id.btnLogout);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, MainActivity.class);
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        db.clearAllTables();
                        Global.getInstance().getSocket().close();
                        FirebaseMessaging.getInstance().deleteToken();
                    }
                };
                new Thread(r).start();
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
                        String usernameToAdd = input.getText().toString().trim();
                        if (!usernameToAdd.isEmpty()) {
                            ChatAPI api = new ChatAPI();
                            api.postChat(usernameToAdd);
                            recyclerView.getAdapter().notifyDataSetChanged();
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
            Global.getInstance().setFBToken(task.getResult());
            FirebaseTokenAPI fbTokenAPI = new FirebaseTokenAPI();
            fbTokenAPI.PostToken(Global.getInstance().getFBToken());
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        Global.getInstance().getSocket().close();
        db.clearAllTables();
    }
}
