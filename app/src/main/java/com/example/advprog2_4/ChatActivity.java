package com.example.advprog2_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advprog2_4.api.MessagesAPI;
import com.example.advprog2_4.objects.MessageItem;
import com.example.advprog2_4.objects.PostMessageRequest;
import com.example.advprog2_4.repositories.MessagesRepository;
import com.example.advprog2_4.viewmodels.MessagesViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private MaterialButton sendButton;
    private EditText messageEditText;
    private MessagesViewModel messagesViewModel;
    private List<MessageItem> msgsReversed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView tvUser = findViewById(R.id.loggedUser);
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("displayName");
        tvUser.setText(displayName);

        de.hdodenhof.circleimageview.CircleImageView contactImage = findViewById(R.id.profilePicView);
        contactImage.setImageBitmap(Global.getInstance().getCurrentContactImage());
        messagesViewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        Global.getInstance().setChatRecyclerView(findViewById(R.id.chatRecyclerView));
        sendButton = findViewById(R.id.sendButton);
        messageEditText = findViewById(R.id.messageEditText);

        messagesViewModel.getMessages().observe(this, messages -> {
            int messageSize = messages.size() - 1;
             msgsReversed = new LinkedList<>();

            for (int i = messageSize; i >= 0; i--) {
                msgsReversed.add(messages.get(i));
            }

            Global.getInstance().getChatRecyclerView().setAdapter(new MessagesAdapter(ChatActivity.this, msgsReversed));
            Global.getInstance().getChatRecyclerView().setLayoutManager(new LinearLayoutManager(this));
            Global.getInstance().getChatRecyclerView().scrollToPosition(Global.getInstance().getChatRecyclerView().getAdapter().getItemCount() - 1);
        });

        Global.getInstance().getChatRenderTrigger().observe(this, str -> {
            messagesViewModel.reload();
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

                    String chatID = String.valueOf(Global.getInstance().getCurrentChatId());
                    Global.getInstance().getSocket().emit("newMessage", Global.getInstance().getCurrentChatId());
                    recreate();
                }
            }
        });

        FloatingActionButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.getInstance().setCurrentChatUsername("");
                Global.getInstance().setCurrentChatId(-1);
                finish();
            }
        });
    }

}
