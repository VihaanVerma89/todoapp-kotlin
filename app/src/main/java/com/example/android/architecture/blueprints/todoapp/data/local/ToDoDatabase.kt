package com.example.android.architecture.blueprints.todoapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.ContentProvider
import android.content.Context
import com.example.android.architecture.blueprints.todoapp.data.Task

/**
 * Created by vihaanverma on 01/03/18.
 */
@Database(entities = arrayOf(Task::class), version = 1)
abstract  class  ToDoDatabase : RoomDatabase(){


    abstract fun taskDao(): TasksDao

    companion object {
        private var INSTANCE: ToDoDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): ToDoDatabase{
            synchronized(lock){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ToDoDatabase::class.java, "Tasks.db")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}