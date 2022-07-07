package com.jdk_to_lsi.userlist

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.ViewModelProvider
import com.jdk_to_lsi.userlist.view.UserDetailsFragment
import com.jdk_to_lsi.userlist.view.UserListFragment
import com.jdk_to_lsi.userlist.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = ViewModelProvider(this).get(UserViewModel::class.java)
        vm.initializeNetworkAndDB(this, this)
        if (isNetworkAvailable()) {
            vm.loadData()
            vm.loadingFinish.observe(this) {
                if (it) {
                    findViewById<TextView>(R.id.dataInformation).visibility = View.GONE
                    findViewById<FrameLayout>(R.id.container).visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, UserListFragment()).commit()
                }
            }
        } else {
            vm.getUsers().observe(this) {
                if (it.isNotEmpty()) {
                    findViewById<TextView>(R.id.dataInformation).visibility = View.GONE
                    findViewById<FrameLayout>(R.id.container).visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, UserListFragment()).commit()
                }
            }
        }

    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}