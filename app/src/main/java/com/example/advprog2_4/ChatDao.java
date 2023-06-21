package com.example.advprog2_4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.advprog2_4.objects.ConvertedChat;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM convertedchat")
    LiveData<List<ConvertedChat>> index();

    @Query("SELECT * FROM convertedchat WHERE id = :id")
    ConvertedChat get(int id);

    @Insert
    void insert(ConvertedChat... convertedChats);

    @Update
    void update(ConvertedChat... convertedChats);

    @Delete
    void delete(ConvertedChat... convertedChats);
}
