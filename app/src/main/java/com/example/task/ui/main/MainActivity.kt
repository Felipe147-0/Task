package com.example.task.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.R
import com.example.task.databinding.ActivityMainBinding
import com.example.task.databinding.ItemTaskBinding
import com.example.task.ui.adapter.TaskAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this). get(MainViewModel::class.java)

        setupRecyclerView()
        setupObservers()


    }
    private fun setupRecyclerView(){
        binding.recyclerTask.layoutManager = LinearLayoutManager(this)
        binding.recyclerTask.adapter = adapter
    }
    private fun setupObservers(){
        viewModel.tasks.observe(this, Observer{adapter.submitDataset(it).adapter.notifyDataSetChange})
    }
}