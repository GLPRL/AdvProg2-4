package com.example.advprog2_4.api;

import android.util.Log;

import com.example.advprog2_4.Global;
import com.example.advprog2_4.MyApplication;
import com.example.advprog2_4.R;
import com.example.advprog2_4.objects.UserIdAndPassword;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public TokenAPI(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Global.getInstance().getServerAddress())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void post(String username, String password) {
        UserIdAndPassword userData = new UserIdAndPassword(username, password);


        Call<String> call = webServiceAPI.postToken("application/json", "*/*" , userData);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String token = response.body();
                if (token == null) {
                    Global.getInstance().setToken("");
                } else {
                    Global.getInstance().setToken(token);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
