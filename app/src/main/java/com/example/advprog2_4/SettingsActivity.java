package com.example.advprog2_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {
    Button btnSetServerAddress;
    FloatingActionButton btnBack;
    Global global;
    EditText etServerAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    btnSetServerAddress = findViewById(R.id.btnSetServerAddress);
    etServerAddress = findViewById(R.id.etServerAddress);
    btnSetServerAddress.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String addr = etServerAddress.getText().toString();
            etServerAddress.setText("");
            if(addr.length()>0){
                global.getInstance().setServerAddress(addr);
                Toast.makeText(SettingsActivity.this, "Server address set successfully", Toast.LENGTH_SHORT).show();
            }
        }
    });
    btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        }
    });
    }
}
