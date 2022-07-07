package com.jdk_to_lsi.userlist.uc

import androidx.lifecycle.LiveData
import com.jdk_to_lsi.userlist.model.User

interface LoadUsersFromSourceUC {
    fun loadUsers()
    fun getUserDetails():User
    fun getAllUsers():LiveData<List<User>>
}