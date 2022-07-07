package com.jdk_to_lsi.userlist.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jdk_to_lsi.userlist.model.User
import com.jdk_to_lsi.userlist.repository.UserRepository

class UserViewModel() : ViewModel() {
    private lateinit var userDetails: User
    private lateinit var userRepository: UserRepository

    val loadingFinish = MutableLiveData<Boolean>(false)
    fun initializeNetworkAndDB(context: Context, lifecycleOwner: LifecycleOwner) {
        userRepository = UserRepository(context, lifecycleOwner)
    }

    fun loadData() {
        userRepository.initData(::isLoadingComplete)
    }

    fun getUsers() = userRepository.getUsers()


    fun isLoadingComplete(isLoaded: Boolean) {
        loadingFinish.postValue(isLoaded)
    }

    fun setViewedUser(user: User) {
        userDetails = user
    }

    fun getUserDetails() = userDetails
}