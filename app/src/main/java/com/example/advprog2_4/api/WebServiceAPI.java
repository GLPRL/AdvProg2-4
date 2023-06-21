package com.example.advprog2_4.api;

import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.ChatContact;
import com.example.advprog2_4.objects.MessageItem;
import com.example.advprog2_4.objects.PostChatResponse;
import com.example.advprog2_4.objects.PostMessageRequest;
import com.example.advprog2_4.objects.PostMessageResponse;
import com.example.advprog2_4.objects.User;
import com.example.advprog2_4.objects.UserIdAndPassword;
import com.example.advprog2_4.objects.UserRegisterObject;
import com.example.advprog2_4.objects.UserRegisterResponse;
import com.example.advprog2_4.objects.UsernameForPostChat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

        @GET("/api/Users/{username}")
        Call<ChatContact> getUser(@Header("Authorization") String authorization ,@Header("accept") String accept ,@Path("username") String username);

        @POST("/api/Users/")
        Call<User> postUser(@Body User user);

        @POST("/api/Tokens")
        Call<String> postToken(@Header("Content-Type") String contentType, @Header("accept") String accept, @Body UserIdAndPassword userData);

        @POST("/api/Users")
        Call<UserRegisterResponse> postUser(@Header("Content-Type") String contentType, @Header("accept") String accept, @Body UserRegisterObject userData);

        @GET("/api/Chats")
        Call<List<Chat>> getAllChats(@Header("Authorization") String authorization, @Header("accept") String accept);

        @POST("/api/Chats")
        Call<PostChatResponse> postChat(@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("accept") String accept, @Body UsernameForPostChat username);

        @GET("/api/Chats/{id}")
        Call<Chat> getChat();

        @GET("/api/Chats/{id}/Messages")
        Call<List<MessageItem>> getMessages(@Header("Authorization") String authorization, @Header("accept") String accept, @Path("id") int id);

        @POST("/api/Chats/{id}/Messages")
        Call<PostMessageResponse> postMessage(@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("accept") String accept, @Path("id") int id, @Body PostMessageRequest msg);
}
