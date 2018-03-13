package com.example.android.architecture.blueprints.todoapp.addedittask

import android.support.design.widget.BottomNavigationView
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

/**
 * Created by vihaan on 3/13/18.
 */

class AddEditPresenter(
        private val taskId: String?,
        val tasksRepository:  TasksRepository,
        val addTaskView : AddEditTasksContract.View,
        override  var  isDataMissing : Boolean
) : AddEditTasksContract.Presenter{

    init {
        addTaskView.presenter = this
    }

    override fun start() {
    }


    override fun saveTask(title: String, description: String) {
        if(taskId == null)
            createTask(title,description)
        else
            updateTask(title,description)
    }

    private fun updateTask(title: String, description: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createTask(title: String, description: String) {
        val newTask = Task(title, description)
        if(newTask.isEmpty)
            addTaskView.showEmptyTaskError()
        else
        {
            tasksRepository.saveTask(newTask)
            addTaskView.showTaskList()
        }

    }

}