package com.example.android.architecture.blueprints.todoapp.tasks

import com.example.android.architecture.blueprints.todoapp.data.Task

/**
 * Created by vihaanverma on 01/03/18.
 */
interface TasksContract{

    interface View : BaseView<Presenter>{

    }

    interface Presenter: BasePresenter{
        fun openTaskDetails(compltedTask: Task)
        fun activateTask(activatedTask: Task)
        fun completeTask(compltedTask: Task)
    }
}