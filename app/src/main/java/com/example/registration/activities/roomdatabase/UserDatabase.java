package com.example.registration.activities.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class} ,version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="db.db";
    private static UserDatabase userDatabase;

    public static synchronized UserDatabase getUserDatabase(Context context) {

        if (userDatabase == null) {
            userDatabase=Room.databaseBuilder(context, UserDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();


        }
        return userDatabase;
    }


    public abstract UserDao userDao();

}
