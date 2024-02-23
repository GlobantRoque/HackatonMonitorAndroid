package com.emmandroid.harrypotterapp.view.model

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.emmandroid.harrypotterapp.databinding.ApiStatusItemBinding

class ApiStatusViewHolder(private var binding: ApiStatusItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        serviceStatus: ServiceStatus,
    ) {
        binding.endpointTextView.text = serviceStatus.name
        binding.statusTextView.text = "${serviceStatus.status}"
        if (serviceStatus.status == ApiStatus.ACTIVE) {
            binding.statusTextView.setTextColor(Color.parseColor("#FF018786"))
        } else {
            binding.statusTextView.setTextColor(Color.parseColor("#CD3434"))
        }
    }
}
