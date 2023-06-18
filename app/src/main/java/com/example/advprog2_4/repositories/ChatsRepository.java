package com.example.advprog2_4.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.advprog2_4.api.ChatAPI;
import com.example.advprog2_4.objects.Chat;

import java.util.LinkedList;
import java.util.List;

public class ChatsRepository {
    private ChatListData chatListData;
    private ChatAPI chatAPI;

    public ChatsRepository() {
        this.chatListData = new ChatListData();
        this.chatAPI = new ChatAPI();
    }

    class ChatListData extends MutableLiveData<List<Chat>> {

        public ChatListData() {
            super();
            List<Chat> chats = new LinkedList<>();
        }

        @Override
        protected void onActive() {
            super.onActive();

            ChatAPI api = new ChatAPI();
            api.getAll(this);
        }

    }



    public LiveData<List<Chat>> getAll() {
        return chatListData;
    }

}


