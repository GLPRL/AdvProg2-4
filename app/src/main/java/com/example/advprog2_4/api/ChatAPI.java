package com.example.advprog2_4.api;

import androidx.lifecycle.MutableLiveData;

import com.example.advprog2_4.Global;
import com.example.advprog2_4.MyApplication;
import com.example.advprog2_4.R;
import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.PostChatResponse;
import com.example.advprog2_4.objects.UsernameForPostChat;
import com.example.advprog2_4.repositories.ChatsRepository;

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

    public void getAll(MutableLiveData<List<Chat>> chatsList) {
        String author = "Bearer " + Global.getInstance().getToken();
        Call<List<Chat>> call = webServiceAPI.getAllChats(author, "text/plain");
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                String token = Global.getInstance().getToken();
                List<Chat> chats = response.body();
                chatsList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
            }
        });
    }

    public void postChat(String contactUsername){
        String author = "Bearer " + Global.getInstance().getToken();
        UsernameForPostChat user = new UsernameForPostChat(contactUsername);
        Call<PostChatResponse> call = webServiceAPI.postChat(author, "application/json", "*/*", user);

        call.enqueue(new Callback<PostChatResponse>() {
            @Override
            public void onResponse(Call<PostChatResponse> call, Response<PostChatResponse> response) {
                String token = Global.getInstance().getToken();
                PostChatResponse chat = response.body();
            }

            @Override
            public void onFailure(Call<PostChatResponse> call, Throwable t) {
            }
        });


    }


}
