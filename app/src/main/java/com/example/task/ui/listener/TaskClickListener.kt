package com.example.task.ui.listener

interface TaskClickListener {
    fun clickDone(position: Int)
    fun clickDelete(taskId: Long)
}