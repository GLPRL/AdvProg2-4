package com.example.advprog2_4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.ConvertedChat;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM chat")
    List<ConvertedChat> index();

    @Query("SELECT * FROM chat WHERE id = :id")
    ConvertedChat get(int id);

    @Insert
    void insert(ConvertedChat... convertedChats);

    @Update
    void update(ConvertedChat... convertedChats);

    @Delete
    void delete(ConvertedChat... convertedChats);
}
