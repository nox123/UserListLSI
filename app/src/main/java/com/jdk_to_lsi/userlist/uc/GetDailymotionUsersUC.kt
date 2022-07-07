package com.jdk_to_lsi.userlist.uc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdk_to_lsi.userlist.api.RetrofitApi
import com.jdk_to_lsi.userlist.model.User
import com.jdk_to_lsi.userlist.model.convertToUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetDailymotionUsersUC : LoadUsersFromSourceUC {

    private val BASE_URL = "https://api.dailymotion.com"

    private val users = MutableLiveData<List<User>>()

    override fun loadUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = RetrofitApi.create(BASE_URL).getDataFromServer2()
                data.body()?.let { data ->
                    users.postValue(data.list.map { it.convertToUser() })
                }
            }catch (e:Exception){
                users.postValue(listOf())
            }
        }
    }

    override fun getUserDetails(): User {
        TODO("Not yet implemented")
    }

    override fun getAllUsers() = users
}