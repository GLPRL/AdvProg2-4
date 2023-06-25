package com.example.advprog2_4.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.advprog2_4.ChatDao;
import com.example.advprog2_4.Global;
import com.example.advprog2_4.api.ChatAPI;
import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.ConvertedChat;

import java.util.LinkedList;
import java.util.List;

public class ChatsRepository {
    private ChatListData chatListData;
    private ChatAPI chatAPI;
    private ChatDao chatDao;

    public ChatsRepository() {
        this.chatListData = new ChatListData();
        this.chatAPI = new ChatAPI();
        this.chatDao = Global.getInstance().getChatDao();
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

    public LiveData<List<ConvertedChat>> getAll() {
        chatAPI.getAll(this.chatListData);
        return chatDao.index();
    }
}