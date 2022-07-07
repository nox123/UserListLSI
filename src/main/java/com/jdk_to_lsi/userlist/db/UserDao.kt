package com.jdk_to_lsi.userlist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jdk_to_lsi.userlist.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE login LIKE :search LIKE :search")
    fun getAll(search:String="%"): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>?)

    @Query("DELETE from User")
    fun deleteAll()
}