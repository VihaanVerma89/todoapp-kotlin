package com.example.android.architecture.blueprints.todoapp.addedittask

import com.example.android.architecture.blueprints.todoapp.tasks.BasePresenter
import com.example.android.architecture.blueprints.todoapp.tasks.BaseView

/**
 * Created by vihaan on 3/13/18.
 */
interface AddEditTasksContract {

    interface View : BaseView<Presenter>
    {
        fun showEmptyTaskError()
        fun showTaskList()

    }

    interface Presenter: BasePresenter{
        var isDataMissing: Boolean
        fun saveTask(toString: String, toString1: String)

    }
}