package com.emmandroid.harrypotterapp.view.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emmandroid.harrypotterapp.databinding.ApiStatusItemBinding

class ApiStatusAdapter(
    private val serviceList: List<ServiceStatus>,
) : RecyclerView.Adapter<ApiStatusViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiStatusViewHolder {
        return ApiStatusViewHolder(ApiStatusItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ApiStatusViewHolder, position: Int) {
        val item = serviceList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int =
        serviceList.size
}
