package com.jdk_to_lsi.userlist.repository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.jdk_to_lsi.userlist.db.UserDatabase
import com.jdk_to_lsi.userlist.model.User
import com.jdk_to_lsi.userlist.uc.GetDailymotionUsersUC
import com.jdk_to_lsi.userlist.uc.GetGitHubUsersUC
import com.jdk_to_lsi.userlist.uc.SaveUserUC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(val context: Context, val lifecycleOwner: LifecycleOwner) {
    private val getGitHubUsersUC = GetGitHubUsersUC()
    private val getDailymotionUsersUC = GetDailymotionUsersUC()
    private val db: UserDatabase = UserDatabase.build(context)
    private val saveUserUC: SaveUserUC = SaveUserUC(db.getUserDao())

    fun initData(dataLoaded: (Boolean) -> Unit) {
        getGitHubUsersUC.loadUsers()
        getGitHubUsersUC.getAllUsers().observe(lifecycleOwner) { userList ->
            if (userList.isNotEmpty()) {
                saveUsersToDB(userList)
                dataLoaded(true)
            }
        }
        getDailymotionUsersUC.loadUsers()
        getDailymotionUsersUC.getAllUsers().observe(lifecycleOwner) { userList ->
            if (userList.isNotEmpty()) {
                saveUsersToDB(userList)
                dataLoaded(true)
            }
        }
    }

    private fun saveUsersToDB(users: List<User>) {
        CoroutineScope(Dispatchers.IO).launch {
            saveUserUC.storeUsersToDB(users)
        }
    }

    fun getUsers() = db.getUserDao().getAll()
}