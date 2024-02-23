package com.emmandroid.harrypotterapp.dashboard.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.emmandroid.harrypotterapp.R
import com.emmandroid.harrypotterapp.databinding.ActivityMainBinding
import com.emmandroid.harrypotterapp.dashboard.view.model.ApiStatusAdapter
import com.emmandroid.harrypotterapp.dashboard.viewmodel.ApiListViewFactoryProvider
import com.emmandroid.harrypotterapp.dashboard.viewmodel.ApiListViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class ApiListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ApiListViewModel
    private lateinit var apiStatusAdapter: ApiStatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ApiListViewFactoryProvider().provide())[ApiListViewModel::class.java]
        setContentView(binding.root)
        setUpPropertyListeners()
        viewModel.onViewCreated()
    }

    private fun setUpPropertyListeners() {
        with(lifecycleScope) {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.apiStatusList.collect {
                        apiStatusAdapter = ApiStatusAdapter(it)
                        binding.apiStatusRecyclerView.adapter = apiStatusAdapter
                        viewModel.onServiceStatusUpdate()
                    }
                }
            }
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.statusUpdateTimeDate.collect {
                        binding.dateTextView.text = getString(R.string.date_last_update, getFormattedDate(it))
                        binding.hourTextView.text = getString(R.string.hour_last_update, getFormattedTime(it))
                    }
                }
            }
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.timeUntilNextUpdate.collect {
                        binding.nextUpdateTextView.text = getString(R.string.next_update, it)
                    }
                }
            }
        }
    }

    private fun getFormattedTime(timeInMillis: Long): String {
        return SimpleDateFormat("HH:mm:ss").format(timeInMillis)
    }

    private fun getFormattedDate(timeInMillis: Long): String {
        return SimpleDateFormat("dd/MM/yyyy").format(timeInMillis)
    }
}
