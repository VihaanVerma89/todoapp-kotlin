package com.example.android.architecture.blueprints.todoapp.tasks

import com.example.android.architecture.blueprints.todoapp.data.Task

/**
 * Created by vihaanverma on 01/03/18.
 */
interface TasksContract{

    interface View : BaseView<Presenter>{
        var isActive: Boolean
        fun showAddTask()
        fun setLoadingIndicator(active: Boolean)
        fun showNoActiveTasks()
        fun showNoCompletedTasks()
        fun showNoTask()
        fun showLoadingTasksError()
        fun showTasks(tasks: List<Task>)
    }

    interface Presenter: BasePresenter{
        var currentFiltering: TasksFilterType
        fun openTaskDetails(compltedTask: Task)
        fun activateTask(activatedTask: Task)
        fun completeTask(compltedTask: Task)
        fun addNewTask()
        fun loadTasks(forceUpdate: Boolean)
        fun clearCompletedTasks()
    }
}