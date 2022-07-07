package com.jdk_to_lsi.userlist.api.converter

import androidx.room.TypeConverter
import com.jdk_to_lsi.userlist.api.DataSource

class UserListTypeConverter {
    @TypeConverter
    fun formDataSource(dataSource: DataSource?):String{
        return dataSource?.name ?: DataSource.GithubSource.name
    }

    @TypeConverter
    fun toDataSource(string: String?):DataSource{
        return when(string){
            DataSource.GithubSource.name -> DataSource.GithubSource
            DataSource.DailyMotionSource.name ->DataSource.DailyMotionSource
            else ->DataSource.GithubSource
        }
    }
}