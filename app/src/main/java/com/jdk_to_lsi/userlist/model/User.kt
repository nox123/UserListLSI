package com.jdk_to_lsi.userlist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jdk_to_lsi.userlist.api.DataSource

@Entity
data class User(
    @PrimaryKey
    val id: Int,
    @SerializedName(value = "login", alternate = arrayOf("screenname"))
    val login: String,
    val avatar_url: String = "",
    var source: DataSource? = null
)

data class DailymotionData(val list: List<DailyUser>)

data class DailyUser(val id: String, val screenname: String)

fun DailyUser.convertToUser(): User {
    return User(
        id = this.id.hashCode(),
        login = this.screenname,
        avatar_url = "https://cdn-icons-png.flaticon.com/512/49/49392.png?w=826&t=st=1657206498~exp=1657207098~hmac=ced869b327f7111bc0daccaa7216ef52210cf4c17cbe349de7d3d5c1668f9910",
        DataSource.DailyMotionSource
    )
}
