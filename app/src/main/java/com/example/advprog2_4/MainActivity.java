package com.example.advprog2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        Button btnLogin =findViewById(R.id.btnLogin);
        EditText etUsername = findViewById(R.id.etUsernameLogin);
        EditText etPassword = findViewById(R.id.etPasswordLogin);
        FloatingActionButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intentSettings = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intentSettings);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Define the navigation logic here
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                if(etUsername.getText().toString().equals("Test") && etPassword.getText().toString().equals("123")) {
                    intent.putExtra("username", etUsername.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Incorrect password and/or username", Toast.LENGTH_LONG).show();
                    etUsername.setText("");
                    etPassword.setText("");
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the navigation logic here
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        EditText etUsername = findViewById(R.id.etUsernameLogin);
        EditText etPassword = findViewById(R.id.etPasswordLogin);
        super.onResume();
        etUsername.setText("");
        etPassword.setText("");
    }

}