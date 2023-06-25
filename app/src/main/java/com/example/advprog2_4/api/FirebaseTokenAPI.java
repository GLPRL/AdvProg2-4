package com.example.advprog2_4.api;

import com.example.advprog2_4.Global;
import com.example.advprog2_4.objects.ConvertedChat;
import com.example.advprog2_4.objects.PostChatResponse;
import com.example.advprog2_4.objects.PostFbToken;
import com.example.advprog2_4.objects.PostFbTokenResponse;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirebaseTokenAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public FirebaseTokenAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Global.getInstance().getServerAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void PostToken(String newToken) {
        String author = "Bearer " + Global.getInstance().getToken();
        PostFbToken aToken = new PostFbToken(newToken);
        Call<PostFbTokenResponse> call = webServiceAPI.postFBToken(author,"application/json" ,"text/plain", Global.getInstance().getUsername(), aToken);
        call.enqueue(new Callback<PostFbTokenResponse>() {
            @Override
            public void onResponse(Call<PostFbTokenResponse> call, Response<PostFbTokenResponse> response) {
                if (response.code() == 200) {
                    String temp = "Response is 200!";
                }
            }

            @Override
            public void onFailure(Call<PostFbTokenResponse> call, Throwable t) {
            }
        });
    }
}
