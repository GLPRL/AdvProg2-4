package com.example.advprog2_4;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advprog2_4.api.MessagesAPI;
import com.example.advprog2_4.objects.MessageItem;
import com.example.advprog2_4.objects.PostMessageRequest;
import com.example.advprog2_4.viewmodels.ChatsViewModel;
import com.example.advprog2_4.viewmodels.MessagesViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private MaterialButton sendButton;
    private RecyclerView chatRecyclerView;
    private EditText messageEditText;
    private List<MessageItem> messageList;
    private MessagesViewModel messagesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView tvUser = findViewById(R.id.loggedUser);
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("displayName");
        tvUser.setText(displayName);
        messagesViewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        sendButton = findViewById(R.id.sendButton);
        messageEditText = findViewById(R.id.messageEditText);

        //messageList = generateMessages();
        //MessagesAdapter adapter = new MessagesAdapter(this, messageList);
        //chatRecyclerView.setAdapter(adapter);
        //chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        messagesViewModel.getMessages().observe(this, messages -> {
            chatRecyclerView.setAdapter(new MessagesAdapter(ChatActivity.this, messages));
            chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            //chatRecyclerView.scrollToPosition(messageList.size() - 1);
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageEditText.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    PostMessageRequest msg = new PostMessageRequest(messageText);
                    MessagesAPI messagesAPI = new MessagesAPI();
                    messagesAPI.postMessage(msg, Global.getInstance().getCurrentChatId());
                    //chatRecyclerView.scrollToPosition(messageList.size() - 1);
                    messageEditText.setText("");
                    recreate();
                }
            }
        });
       FloatingActionButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
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
