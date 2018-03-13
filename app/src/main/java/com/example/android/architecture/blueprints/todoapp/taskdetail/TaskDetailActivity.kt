package com.example.android.architecture.blueprints.todoapp.taskdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.util.setupActionBar

/**
 * Created by vihaan on 3/13/18.
 */
class TaskDetailActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taskdetail_act)

        setupActionBar(R.id.toolbar){
            setDisplayHomeAsUpEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        val taskId = intent.getStringExtra(EXTRA_TASK_ID)
//        supportFragmentManager.findFragmentById(R.id.contentFrame) as TaskDetailF
    }

    companion object {
        const val EXTRA_TASK_ID = "TASK_ID"
    }
}
