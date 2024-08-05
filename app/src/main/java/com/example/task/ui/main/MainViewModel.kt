package com.example.task.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.data.model.Task
import com.example.task.data.repository.TasksRepository
import com.example.task.dto.TaskDto

class MainViewModel: ViewModel() {

    private val repository = TasksRepository()

    private val _tasks = MutableLiveData<List<TaskDto>>()
    val tasks: LiveData<List<TaskDto>>
        get() {
            return _tasks
        }

    private val _insertedTask = MutableLiveData<Boolean>()
    val insertedTask: LiveData<Boolean> = _insertedTask

    private val _updateTask = MutableLiveData<Boolean>()
    val updateTask: LiveData<Boolean> get() = _updateTask

    init {
        loadData()
    }

    fun addTask(str: String) {
        val task = Task(str, false)
        repository.insert(task)
        _insertedTask.value = true
        loadData()
    }

    fun handleDone(position: Int) {
        val dto = _tasks.value?.get(position)
        if (dto != null) {
            val task = repository.findById(dto.id)
            task.isCompleted = !task.isCompleted
            _updateTask.value = true
            loadData()
        }
    }

    private fun loadData(){
        val taskList = repository.findAll()
        val taskDtoList = taskList.map {
            task: Task -> TaskDto(task.id, task.description, task.isCompleted)
        }
        _tasks.value = taskDtoList
    }
    fun deleteTask(taskId: Long){
        repository.delete(taskId)
        loadData()
    }
}