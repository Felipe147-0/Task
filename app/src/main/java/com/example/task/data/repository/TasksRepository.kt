package com.example.task.data.repository

import com.example.task.data.dao.TaskDao
import com.example.task.data.model.Task

class TasksRepository {
    private val dao = TaskDao

    fun findAll(): List<Task> {
        return dao.getAllTask()
    }

    fun findBtId(id: Long): Task {
        return dao.getTask(id)
    }

    fun insert(task: Task) {
        dao.addTask(task)
    }
}