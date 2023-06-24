package com.example.advprog2_4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.advprog2_4.api.WebServiceAPI;
import com.example.advprog2_4.objects.ChatContact;
import com.example.advprog2_4.objects.UserIdAndPassword;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Global.getInstance().setToken("");
        Global.getInstance().setUsername("");
        Global.getInstance().setPassword("");

        Button btnSignUp = findViewById(R.id.btnSignUp);
        Button btnLogin = findViewById(R.id.btnLogin);
        EditText etUsername = findViewById(R.id.etUsernameLogin);
        EditText etPassword = findViewById(R.id.etPasswordLogin);
        FloatingActionButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // http://10.0.2.2:5000
                if (Global.getInstance().getServerAddress().compareTo("") != 0) {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    Global.getInstance().setUsername(username);
                    Global.getInstance().setPassword(password);
                    Intent intent = new Intent(MainActivity.this, ContactsActivity.class);

                    Retrofit retrofit;
                    WebServiceAPI webServiceAPI;
                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();

                    retrofit = new Retrofit.Builder()
                            .baseUrl(Global.getInstance().getServerAddress())
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                    webServiceAPI = retrofit.create(WebServiceAPI.class);

                    UserIdAndPassword userData = new UserIdAndPassword(username, password);


                    Call<String> call = webServiceAPI.postToken("application/json", "*/*", userData);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            String token = response.body();
                            if (token == null) {
                                Global.getInstance().setToken("");
                                Toast.makeText(getApplicationContext(), "Incorrect password and/or username", Toast.LENGTH_LONG).show();
                                etUsername.setText("");
                                etPassword.setText("");
                            } else {
                                Global.getInstance().setToken(token);

                                Retrofit retrofit;
                                WebServiceAPI webServiceAPI;
                                Gson gson = new GsonBuilder()
                                        .setLenient()
                                        .create();

                                retrofit = new Retrofit.Builder()
                                        .baseUrl(Global.getInstance().getServerAddress())
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .build();
                                webServiceAPI = retrofit.create(WebServiceAPI.class);
                                String userToken = "Bearer " + Global.getInstance().getToken();

                                Call<ChatContact> call2 = webServiceAPI.getUser(userToken, "text/plain", Global.getInstance().getUsername());
                                call2.enqueue(new Callback<ChatContact>() {
                                    @Override
                                    public void onResponse(Call<ChatContact> call, Response<ChatContact> response) {
                                        ChatContact userDetails = response.body();
                                        if (response.code() == 200) {
                                            byte[] imageInBase64 = Base64.decode(userDetails.getProfilePic(), Base64.DEFAULT);
                                            Bitmap imageBitMap = BitmapFactory.decodeByteArray(imageInBase64, 0, imageInBase64.length);
                                            Global.getInstance().setUserProfilePic(imageBitMap);
                                            Global.getInstance().setUserDisplayName(userDetails.getDisplayName());
                                            intent.putExtra("username", username);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ChatContact> call, Throwable t) {

                                    }
                                });
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Enter a server address in the Settings (top left)", Toast.LENGTH_LONG).show();
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
        Global.getInstance().setToken("");
    }

}