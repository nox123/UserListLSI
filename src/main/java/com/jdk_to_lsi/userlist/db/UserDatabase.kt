package com.jdk_to_lsi.userlist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jdk_to_lsi.userlist.api.converter.UserListTypeConverter
import com.jdk_to_lsi.userlist.model.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
@TypeConverters(UserListTypeConverter::class)
abstract class UserDatabase: RoomDatabase() {

    abstract fun getUserDao():UserDao

    companion object{
        fun build(context:Context) = Room.databaseBuilder(context,UserDatabase::class.java,"db").build()
    }
}