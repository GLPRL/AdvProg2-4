package com.example.advprog2_4.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.advprog2_4.Global;
import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.MessageItem;
import com.example.advprog2_4.repositories.ChatsRepository;
import com.example.advprog2_4.repositories.MessagesRepository;

import java.util.List;

public class MessagesViewModel extends ViewModel {

    private MessagesRepository messagesRepository;
    private LiveData<List<MessageItem>> messages;
    private int chatId;

    public MessagesViewModel() {
        this.chatId = Global.getInstance().getCurrentChatId();
        this.messagesRepository = new MessagesRepository(Global.getInstance().getCurrentChatId());
        this.messages = messagesRepository.getMessages();
    }

    public LiveData<List<MessageItem>> getMessages() {
        return messages;
    }
}
