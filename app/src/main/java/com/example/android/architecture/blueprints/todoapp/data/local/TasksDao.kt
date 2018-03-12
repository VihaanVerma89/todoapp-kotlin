package com.example.android.architecture.blueprints.todoapp.data.local

import android.arch.persistence.room.*
import android.view.View
import com.example.android.architecture.blueprints.todoapp.data.Task

/**
 * Created by vihaanverma on 10/03/18.
 */

@Dao interface TasksDao{

    @Query("SELECT * FROM Tasks") fun getTasks(): List<Task>
    @Query("SELECT * FROM Tasks where entryId = :taskId") fun getTaskById(taskId: String): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertTask(task: Task)
    @Update fun updateTask(task: Task): Int
    @Query("UPDATE tasks SET completed = :completed WHERE entryid = :taskId")
    fun updateComplted(taskId: String, completed: Boolean)


}