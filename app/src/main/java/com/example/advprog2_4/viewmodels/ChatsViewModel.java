package com.example.advprog2_4.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.repositories.ChatsRepository;

import java.util.List;

public class ChatsViewModel extends ViewModel {
    private ChatsRepository chatsRepository;
    private LiveData<List<Chat>> chats;

    public ChatsViewModel() {
        this.chatsRepository = new ChatsRepository();
        this.chats = chatsRepository.getAll();
    }

    public LiveData<List<Chat>> getChats() {
        return chats;
    }
}
