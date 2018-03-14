package com.example.android.architecture.blueprints.todoapp.tasks

import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

/**
 * Created by vihaanverma on 01/03/18.
 */

class TasksPresenter(val tasksRepository: TasksRepository,
                     val tasksView: TasksContract.View)
    : TasksContract.Presenter {

    override var currentFiltering = TasksFilterType.ALL_TASKS
    private var firstLoad = true

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
        loadTasks(false)
    }

    override fun loadTasks(forceUpdate: Boolean) {
        loadTasks(forceUpdate || firstLoad, true)
        firstLoad = false
    }

    private fun loadTasks(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI)
            tasksView.setLoadingIndicator(true)

        if (forceUpdate)
            tasksRepository.refreshTasks()

        tasksRepository.getTasks(object : TasksDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                val tasksToShow = ArrayList<Task>()

                for (task in tasks) {
                    when (currentFiltering) {
                        TasksFilterType.ALL_TASKS -> tasksToShow.add(task)
                        TasksFilterType.ACTIVE_TASKS -> if (task.isActive) {
                            tasksToShow.add(task)
                        }
                        TasksFilterType.COMPLETED_TASKS -> if (task.isCompleted) {
                            tasksToShow.add(task)
                        }
                    }
                }

                if (!tasksView.isActive) {
                    return
                }

                if (showLoadingUI) {
                    tasksView.setLoadingIndicator(false)
                }

                processTasks(tasksToShow)
            }

            override fun onDataNotAvailable() {
                if (!tasksView.isActive) {
                    return
                }
                tasksView.showLoadingTasksError()
            }

        })
    }

    private fun processTasks(tasks: List<Task>) {
        if (tasks.isEmpty()) {
            processEmptyTasks()
        }else{
            tasksView.showTasks(tasks)
        }
    }

    private fun processEmptyTasks() {
        when (currentFiltering) {
            TasksFilterType.ACTIVE_TASKS -> tasksView.showNoActiveTasks()
            TasksFilterType.COMPLETED_TASKS -> tasksView.showNoCompletedTasks()
            else -> tasksView.showNoTask()
        }
    }

    override fun addNewTask() {
        tasksView.showAddTask()
    }

    override fun clearCompletedTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}