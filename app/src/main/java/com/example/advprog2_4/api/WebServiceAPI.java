package com.example.advprog2_4.api;

import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.User;
import com.example.advprog2_4.objects.UserIdAndPassword;
import com.example.advprog2_4.objects.UserRegisterObject;
import com.example.advprog2_4.objects.UserRegisterResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

        @GET("Users/{username}")
        Call<User> getUser(@Path("username") String username);

        @POST("Users/")
        Call<User> postUser(@Body User user);

        @POST("Tokens")
        Call<String> postToken(@Header("Content-Type") String contentType, @Header("accept") String accept, @Body UserIdAndPassword userData);

        @POST("Users")
        Call<UserRegisterResponse> postUser(@Header("Content-Type") String contentType, @Header("accept") String accept, @Body UserRegisterObject userData);

        @GET("Chats")
        Call<List<Chat>> getAllChats(@Header("Authorization") String authorization, @Header("accept") String accept);

        @POST("Chats")
        Call<Chat> postChat();

        @GET("Chats/{id}")
        Call<Chat> getChat();
}
