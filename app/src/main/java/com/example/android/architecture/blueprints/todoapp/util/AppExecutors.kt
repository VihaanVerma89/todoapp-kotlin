package com.example.android.architecture.blueprints.todoapp.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by vihaanverma on 10/03/18.
 */

const val THREAD_COUNT = 3;

open class AppExecutors constructor(val diskIO: Executor = DiskIOThreadExecutor(),
                                    val networkIO: Executor = Executors.newFixedThreadPool
                                    (THREAD_COUNT),
                                    val mainThread: Executor = MainThreadExecutor())
{

    private class MainThreadExecutor : Executor{
        private val mainThreadExecutor = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable?) {
            mainThreadExecutor.post(command)
        }
    }
}