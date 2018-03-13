package com.example.android.architecture.blueprints.todoapp.tasks

import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

/**
 * Created by vihaanverma on 01/03/18.
 */

class TasksPresenter(val tasksRepository: TasksRepository,
                     val tasksView: TasksContract.View)
    : TasksContract.Presenter {

    init {
        tasksView.presenter = this
    }
    override fun openTaskDetails(compltedTask: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activateTask(activatedTask: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun completeTask(compltedTask: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewTask() {
        tasksView.showAddTask()
    }

}