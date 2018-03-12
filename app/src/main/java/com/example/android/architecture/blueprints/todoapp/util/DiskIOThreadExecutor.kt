package com.example.android.architecture.blueprints.todoapp.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by vihaanverma on 10/03/18.
 */
class DiskIOThreadExecutor: Executor{

    private val diskIO = Executors.newSingleThreadExecutor()
    override fun execute(command: Runnable?) {
        diskIO.execute(command)
    }
}