package com.jdk_to_lsi.userlist.uc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdk_to_lsi.userlist.api.RetrofitApi
import com.jdk_to_lsi.userlist.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetGitHubUsersUC:LoadUsersFromSourceUC {
    private val BASE_URL= "https://api.github.com"

    private val users = MutableLiveData<List<User>>()

    override fun loadUsers() {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val data = RetrofitApi.create(BASE_URL).getDataFromServer()
                users.postValue(data.body())
            }
        }catch (e:Exception){
            users.postValue(listOf())
        }
    }

    override fun getUserDetails(): User {
        TODO("Not yet implemented")
    }

    override fun getAllUsers() = users
}