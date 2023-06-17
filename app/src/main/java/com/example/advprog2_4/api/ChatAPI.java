package com.example.advprog2_4.api;

import com.example.advprog2_4.Global;
import com.example.advprog2_4.MyApplication;
import com.example.advprog2_4.R;
import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ChatAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void getAll() {
        String author = "Bearer " + Global.getInstance().getToken();
        Call<List<Chat>> call = webServiceAPI.getAllChats(author, "text/plain");
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                String token = Global.getInstance().getToken();
                List<Chat> chats = response.body();
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
            }
        });
    }


}
