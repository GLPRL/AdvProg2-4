package com.example.advprog2_4.api;

import com.example.advprog2_4.objects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

        @GET("Users/{username}")
        Call<User> getUser(@Path("username") String username);

        @POST("Users/")
        Call<User> postUser(@Body User user);
}
