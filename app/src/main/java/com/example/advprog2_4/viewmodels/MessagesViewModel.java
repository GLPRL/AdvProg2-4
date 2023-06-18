package com.example.advprog2_4.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.MessageItem;
import com.example.advprog2_4.repositories.ChatsRepository;
import com.example.advprog2_4.repositories.MessagesRepository;

import java.util.List;

public class MessagesViewModel {

    private MessagesRepository messagesRepository;
    private LiveData<List<MessageItem>> messages;
    private int chatId;

    public MessagesViewModel(int chatId) {
        this.chatId = chatId;
        this.messagesRepository = new MessagesRepository(chatId);
        //this.messages = messagesRepository.;
    }
}
