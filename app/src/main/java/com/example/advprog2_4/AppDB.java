package com.example.advprog2_4;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.advprog2_4.objects.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
}
