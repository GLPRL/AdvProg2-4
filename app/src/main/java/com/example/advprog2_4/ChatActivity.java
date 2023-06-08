package com.example.advprog2_4;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private MaterialButton sendButton;
    private RecyclerView chatRecyclerView;
    private EditText messageEditText;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        TextView tvUser = findViewById(R.id.loggedUser);
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("displayName");
        tvUser.setText(displayName);

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        sendButton = findViewById(R.id.sendButton);
        messageEditText = findViewById(R.id.messageEditText);

        messageList = generateMessages();
        MessagesAdapter adapter = new MessagesAdapter(messageList);
        chatRecyclerView.setAdapter(adapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageEditText.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    Message newMessage = new Message(messageText, 1);
                    messageList.add(newMessage);
                    adapter.notifyItemInserted(messageList.size() - 1);
                    chatRecyclerView.scrollToPosition(messageList.size() - 1);
                    messageEditText.setText("");
                }
            }
        });
    }

    public List<Message> generateMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Hello", 1)); // Right-aligned message
        messages.add(new Message("Hi", 0)); // Left-aligned message
        messages.add(new Message("How are you?", 1)); // Right-aligned message
        messages.add(new Message("I'm good, thanks!", 0)); // Left-aligned message
        messages.add(new Message("Hello", 1)); // Right-aligned message
        messages.add(new Message("Hi", 0)); // Left-aligned message
        messages.add(new Message("How are you?", 1)); // Right-aligned message
        messages.add(new Message("I'm good, thanks!", 0)); // Left-aligned message
        messages.add(new Message("Hello", 1)); // Right-aligned message
        messages.add(new Message("Hi", 0)); // Left-aligned message
        messages.add(new Message("How are you?", 1)); // Right-aligned message
        messages.add(new Message("I'm good, thanks!", 0)); // Left-aligned message
        messages.add(new Message("Hello", 1)); // Right-aligned message
        messages.add(new Message("Hi", 0)); // Left-aligned message
        messages.add(new Message("How are you?", 1)); // Right-aligned message
        messages.add(new Message("I'm good, thanks!", 0)); // Left-aligned message
        messages.add(new Message("I'm good, thanks!", 0)); // Left-aligned message
        messages.add(new Message("I'm good, thanks!", 0)); // Left-aligned message
        return messages;
    }
}
