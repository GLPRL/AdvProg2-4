package com.example.advprog2_4.api;

import androidx.lifecycle.MutableLiveData;

import com.example.advprog2_4.MyApplication;
import com.example.advprog2_4.R;
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
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get(String username) {
        Call<User> call = webServiceAPI.getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User user = response.body();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

}
