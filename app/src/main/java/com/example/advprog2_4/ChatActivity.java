package com.example.advprog2_4;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView tvUser = findViewById(R.id.loggedUser);
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("displayName");
        tvUser.setText(displayName);
        RecyclerView recyclerView = findViewById(R.id.chatRecyclerView);

// Create a list of messages
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Hello", 1)); // Right-aligned message
        messages.add(new Message("Hi", 0)); // Left-aligned message
        messages.add(new Message("How are you?", 1)); // Right-aligned message
        messages.add(new Message("I'm good, thanks!", 0)); // Left-aligned message

// Create the adapter and set it on the RecyclerView
        MessagesAdapter adapter = new MessagesAdapter(messages);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
