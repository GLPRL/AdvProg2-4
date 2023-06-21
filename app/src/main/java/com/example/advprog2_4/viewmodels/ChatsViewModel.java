package com.example.advprog2_4.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.advprog2_4.objects.ConvertedChat;
import com.example.advprog2_4.repositories.ChatsRepository;

import java.util.List;

public class ChatsViewModel extends ViewModel {
    private ChatsRepository chatsRepository;
    private LiveData<List<ConvertedChat>> chats;

    public ChatsViewModel() {
        this.chatsRepository = new ChatsRepository();
        this.chats = chatsRepository.getAll();
    }

    public LiveData<List<ConvertedChat>> getChats() {
        return chatsRepository.getAll();
    }
}
