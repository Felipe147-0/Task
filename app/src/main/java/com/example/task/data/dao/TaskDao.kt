package com.example.task.data.dao

import com.example.task.data.model.Task

object TaskDao {
    private val tasks = mutableListOf<Task>()

    fun getAllTask(): List<Task> {
        return tasks
    }

    fun addTask(task: Task){
        tasks.add(task)
    }

    fun getTask(id: Long): Task{
        return tasks.stream().filter { item -> item.id == id }.findFirst().orElse(null)
    }
    fun deleteTask(id: Long){
        tasks.removeIf { it.id == id }
    }
}