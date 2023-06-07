package com.example.advprog2_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView tvUser = findViewById(R.id.loggedUser);
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("displayName");
        tvUser.setText(displayName);

    }
}
