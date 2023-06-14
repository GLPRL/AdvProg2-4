package com.example.advprog2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.advprog2_4.api.TokenAPI;
import com.example.advprog2_4.api.UserAPI;
import com.example.advprog2_4.api.WebServiceAPI;
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


        //UserAPI userAPI = new UserAPI();
        //userAPI.get("ng");

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
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);

                Retrofit retrofit;
                WebServiceAPI webServiceAPI;
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                retrofit = new Retrofit.Builder()
                        .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                webServiceAPI = retrofit.create(WebServiceAPI.class);

                UserIdAndPassword userData = new UserIdAndPassword(username, password);


                Call<String> call = webServiceAPI.postToken("application/json", "*/*" , userData);
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
                            intent.putExtra("username", username);
                            startActivity(intent);
                        }

                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                    }
                });
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