package com.example.advprog2_4.api;

import androidx.lifecycle.MutableLiveData;

import com.example.advprog2_4.Global;
import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.ConvertedChat;
import com.example.advprog2_4.objects.PostChatResponse;
import com.example.advprog2_4.objects.UsernameForPostChat;
import com.google.firebase.messaging.FirebaseMessaging;

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
                .baseUrl(Global.getInstance().getServerAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    /**
     * Get all chats from server
     * @param chatsList chatslist
     */
    public void getAll(MutableLiveData<List<Chat>> chatsList) {
        String author = "Bearer " + Global.getInstance().getToken();
        Call<List<Chat>> call = webServiceAPI.getAllChats(author, "text/plain");
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                String token = Global.getInstance().getToken();
                chatsList.setValue(response.body());
                for (int i = 0; i < response.body().size(); i++) {
                    String created = "";
                    if (response.body().get(i).getLastMessage() != null){
                        created = response.body().get(i).getLastMessage().getCreated();
                    }
                    ConvertedChat temp = new ConvertedChat(response.body().get(i).getId(), response.body().get(i).getUser().getDisplayName(), response.body().get(i).getUser().getProfilePic(), created, response.body().get(i).getUser().getUsername());
                    Global.getInstance().getChatDao().insert(temp);
                    //Subscribe to topics. Each topic is a chatID
                    String s = String.valueOf(response.body().get(i).getId());
                    FirebaseMessaging.getInstance().subscribeToTopic(s);
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
            }
        });
    }

    /**
     * Create new chat
     */
    public void postChat(String contactUsername){
        String author = "Bearer " + Global.getInstance().getToken();
        UsernameForPostChat user = new UsernameForPostChat(contactUsername);
        Call<PostChatResponse> call = webServiceAPI.postChat(author, "application/json", "*/*", user);

        call.enqueue(new Callback<PostChatResponse>() {
            @Override
            public void onResponse(Call<PostChatResponse> call, Response<PostChatResponse> response) {
                if (response.code() == 200) {
                    String token = Global.getInstance().getToken();
                    PostChatResponse chat = response.body();
                    ConvertedChat temp = new ConvertedChat(chat.getId(), chat.getContact().getDisplayName(), chat.getContact().getProfilePic(), "", "");
                    Global.getInstance().getChatDao().insert(temp);
                    String s = String.valueOf(chat.getId());
                    FirebaseMessaging.getInstance().subscribeToTopic(s);
                }
            }

            @Override
            public void onFailure(Call<PostChatResponse> call, Throwable t) {
            }
        });
    }
}
