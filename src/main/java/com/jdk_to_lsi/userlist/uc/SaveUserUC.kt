package com.jdk_to_lsi.userlist.uc

import com.jdk_to_lsi.userlist.db.UserDao
import com.jdk_to_lsi.userlist.model.User

class SaveUserUC(private val usersDao:UserDao) {
    suspend fun storeUsersToDB(users:List<User>){
        usersDao.insertAll(users)
    }
}