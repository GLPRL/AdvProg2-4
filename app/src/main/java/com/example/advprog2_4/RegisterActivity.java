package com.example.advprog2_4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && data!= null){
            Uri selectedImage= data.getData();
            ImageView image = findViewById(R.id.uploadImage);
            image.setImageURI(selectedImage);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        EditText etUsernameLogin = findViewById(R.id.etUsernameLogin);
        EditText etDisplay = findViewById(R.id.etDisplayName);
        EditText etPassword = findViewById(R.id.etPasswordLogin);
        EditText etVerifyPassword = findViewById(R.id.verifyPassword);
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        Button btnLoginPage = findViewById(R.id.btnLoginPage);
        Button btnUpload = findViewById(R.id.btnUpload);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });
        btnLoginPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder messageBuilder = new StringBuilder();
                boolean validRegister = true;
                String username = etUsernameLogin.getText().toString();
                String displayName= etDisplay.getText().toString();
                String password = etPassword.getText().toString();
                String verifyPassword = etVerifyPassword.getText().toString();
                if (username.length() <2 || username.length() >16) {
                    messageBuilder.append("Username should be 2-16 characters.\n\n");
                    validRegister=false;
                }
                if(displayName.length()<2 || displayName.length()>16) {
                    messageBuilder.append("Display name should be 2-16 characters.\n\n");
                    validRegister=false;
                }
                if (!password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*") || password.length() <6 || password.length()>16) {
                    messageBuilder.append("Password should contain both letters and numbers. 8-16 in length\n\n");
                    validRegister = false;
                }
                if (!verifyPassword.equals(password)) {
                    messageBuilder.append("Password and Verify Password do not match.\n\n");
                    validRegister = false;
                }
                if(!validRegister) {
                    builder.setTitle("Validation Error");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Handle OK button click
                        }
                    });
                    builder.setMessage(messageBuilder.toString());
                    builder.show();
                }
                else{
                    builder.setTitle("Registered Successfully");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setMessage("");
                    builder.show();
                }
            }
        });
    }


}
