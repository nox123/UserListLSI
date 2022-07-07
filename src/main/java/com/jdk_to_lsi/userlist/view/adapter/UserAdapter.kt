package com.jdk_to_lsi.userlist.view.adapter

import android.os.Binder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jdk_to_lsi.userlist.api.DataSource
import com.jdk_to_lsi.userlist.databinding.UserListItemBinding
import com.jdk_to_lsi.userlist.model.User
import java.util.zip.Inflater

class UserAdapter(val onClickAction:(User)->Unit):RecyclerView.Adapter<UserViewHolder>() {
    private val items= mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position],onClickAction)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setUsers(users: List<User>) {
        items.clear()
        items.addAll(users)
        notifyDataSetChanged()
    }
}

class UserViewHolder(val binding:UserListItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(user: User,onClickAction:(User)->Unit) {
        binding.name.text = user.login
        Glide.with(binding.root.context).load(user.avatar_url).into(binding.avatar)
        binding.source.text = user.source?.name ?: DataSource.GithubSource.name
        binding.root.setOnClickListener {
            onClickAction(user)
        }
    }

}