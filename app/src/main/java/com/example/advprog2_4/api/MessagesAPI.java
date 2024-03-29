package com.example.advprog2_4.api;

import androidx.lifecycle.MutableLiveData;

import com.example.advprog2_4.Global;
import com.example.advprog2_4.objects.MessageItem;
import com.example.advprog2_4.objects.PostMessageRequest;
import com.example.advprog2_4.objects.PostMessageResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessagesAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public MessagesAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Global.getInstance().getServerAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void getMessages(MutableLiveData<List<MessageItem>> messagesList,int id) {
        String author = "Bearer " + Global.getInstance().getToken();
        Call<List<MessageItem>> call = webServiceAPI.getMessages(author, "*/*", id);
        call.enqueue(new Callback<List<MessageItem>>() {
            @Override
            public void onResponse(Call<List<MessageItem>> call, Response<List<MessageItem>> response) {
                String token = Global.getInstance().getToken();
                List<MessageItem> messages = response.body();
                messagesList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MessageItem>> call, Throwable t) {
            }
        });
    }

    public void postMessage(PostMessageRequest msg, int id) {
        String author = "Bearer " + Global.getInstance().getToken();
        Call<PostMessageResponse> call = webServiceAPI.postMessage(author,"application/json", "*/*", id, msg);
        call.enqueue(new Callback<PostMessageResponse>() {
            @Override
            public void onResponse(Call<PostMessageResponse> call, Response<PostMessageResponse> response) {
                PostMessageResponse resp = response.body();
            }

            @Override
            public void onFailure(Call<PostMessageResponse> call, Throwable t) {
            }
        });
    }


}
