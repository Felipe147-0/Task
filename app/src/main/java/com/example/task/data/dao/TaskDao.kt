package com.example.task.data.dao

import com.example.task.data.model.Task

object TaskDao {
    private val tasks = mutableListOf<Task>()

    fun getAllTask(): List<Task> = tasks

    fun addTask(task: Task){
        tasks.add(task)
    }

    fun getTask(id: Long): Task{
        return tasks.stream().filter { item -> item.id == id }.findFirst().orElse(null)
    }
}