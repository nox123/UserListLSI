package com.jdk_to_lsi.userlist.api

sealed class DataSource (val url:String,val name:String){
    object GithubSource:DataSource("https://api.github.com","Github")
    object DailyMotionSource:DataSource("https://api.dailymotion.com","DailyMotion")
}