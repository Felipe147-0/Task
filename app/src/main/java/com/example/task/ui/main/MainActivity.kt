package com.example.task.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.R
import com.example.task.databinding.ActivityMainBinding
import com.example.task.databinding.DialogTaskBinding
import com.example.task.databinding.ItemTaskBinding
import com.example.task.ui.adapter.TaskAdapter
import com.example.task.ui.listener.TaskClickListener
import com.google.android.filament.View

class MainActivity :
    AppCompatActivity(),
    View.OnClickListener,
    TaskClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = TaskAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this). get(MainViewModel::class.java)

        setupRecyclerView()
        setupObservers()
        setupListener()

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_add) {
            newTask()
        }
    }

    private fun setupRecyclerView(){
        binding.recyclerTask.layoutManager = LinearLayoutManager(this)
        binding.recyclerTask.adapter = adapter
    }
    private fun setupObservers(){
        viewModel.tasks.observe(this,
            Observer{adapter.submitDataset(it)
            adapter.notifyDataSetChange()
        })

        viewModel.insertedTask.observe(this, Observer {
            val str: String = if (it) {
                "Tarefa inserida com sucesso!"
            } else {
                "Erro ao inserir a tarefa!"
            }

            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        })
    }
    private fun setupListener() {
        binding.buttonAdd.setOnClickListener(this)
    }

    private fun newTask() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_task, null)

        val bindingDialog: DialogTaskBinding = DialogTaskBinding.bind(dialogView)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Adicionar nova Tarefa")
            .setPositiveButton("Salvar") { dialog, _ ->
                val str = bindingDialog.editDescription.text.toString()
                viewModel.addTask(str)
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }
    override fun clickDone(position: Int) {
        viewModel.handleDone(position)
    }

    override fun clickDelete(taskId: Long) {
        viewModel.deleteTask(taskId)
    }

}