package com.example.advprog2_4.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.advprog2_4.api.ChatAPI;
import com.example.advprog2_4.api.MessagesAPI;
import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.MessageItem;

import java.util.LinkedList;
import java.util.List;

public class MessagesRepository {

    private MessageListData messageListData;
    private MessagesAPI messagesAPI;
    private int chatId;

    public MessagesRepository(int chatId) {
        this.messageListData = new MessageListData(chatId);
        this.messagesAPI = new MessagesAPI();
        this.chatId = chatId;
    }

    class MessageListData extends MutableLiveData<List<MessageItem>> {
        private int chatId;
        public MessageListData(int chatId) {
            super();
            this.chatId = chatId;
            List<MessageItem> chats = new LinkedList<>();
        }

        @Override
        protected void onActive() {
            super.onActive();

            MessagesAPI api = new MessagesAPI();
            api.getMessages(this, chatId);
        }

    }
}
