package com.example.advprog2_4.api;

import androidx.lifecycle.MutableLiveData;

import com.example.advprog2_4.Global;
import com.example.advprog2_4.objects.ChatContact;
import com.example.advprog2_4.objects.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    private MutableLiveData<User> userData;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Global.getInstance().getServerAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get(String username) {
        Call<ChatContact> call = webServiceAPI.getUser(Global.getInstance().getToken(),"text/plain", Global.getInstance().getUsername());
        call.enqueue(new Callback<ChatContact>() {
            @Override
            public void onResponse(Call<ChatContact> call, Response<ChatContact> response) {

                ChatContact user = response.body();

            }

            @Override
            public void onFailure(Call<ChatContact> call, Throwable t) {
            }
        });
    }

}
