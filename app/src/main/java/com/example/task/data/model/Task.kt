package com.example.task.data.model

class Task (va_description: String, var isCompleted: Boolean){
    private companion object{
        var lastId: Long = 1L
    }

    var id: Long = 0L

    init {
        lastId += 1
        id = lastId
    }

}