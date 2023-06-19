package com.example.advprog2_4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.advprog2_4.objects.Chat;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM chat")
    List<Chat> index();

    @Query("SELECT * FROM chat WHERE id = :id")
    Chat get(int id);

    @Insert
    void insert(Chat... chats);

    @Update
    void update(Chat... chats);

    @Delete
    void delete(Chat... chats);
}
