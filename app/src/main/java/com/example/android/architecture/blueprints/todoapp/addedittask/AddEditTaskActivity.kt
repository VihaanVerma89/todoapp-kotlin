package com.example.android.architecture.blueprints.todoapp.addedittask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.architecture.blueprints.todoapp.Injection
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.util.replaceFragmentInActivity
import com.example.android.architecture.blueprints.todoapp.util.setupActionBar

/**
 * Created by vihaan on 3/13/18.
 */
class AddEditTaskActivity : AppCompatActivity(){

    private lateinit var addEditPresenter: AddEditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtask_act)
        val taskId = intent.getStringExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID)

        setupActionBar(R.id.toolbar){
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setTitle(if(taskId == null) R.string.add_task else R.string.edit_task)
        }

        val addEditTaskFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as AddEditTaskFragment? ?: AddEditTaskFragment.newInstance(taskId).also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        val shouldLoadDataFromRepo = savedInstanceState?.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY)
        ?: true

        addEditPresenter = AddEditPresenter(
                taskId,
                Injection.provideTasksRepository(applicationContext),
                addEditTaskFragment,
                shouldLoadDataFromRepo
        )


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY"
        const val REQUEST_ADD_TASK = 1
    }
}