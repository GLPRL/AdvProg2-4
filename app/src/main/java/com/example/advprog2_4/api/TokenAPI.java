package com.example.advprog2_4.api;

import com.example.advprog2_4.MyApplication;
import com.example.advprog2_4.R;
import com.example.advprog2_4.objects.UserIdAndPassword;
import com.google.gson.Gson;
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

    public TokenAPI(){
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
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

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
