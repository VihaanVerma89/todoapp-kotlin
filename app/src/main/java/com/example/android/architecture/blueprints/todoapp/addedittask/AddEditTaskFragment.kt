package com.example.android.architecture.blueprints.todoapp.addedittask

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.architecture.blueprints.todoapp.R

/**
 * Created by vihaan on 3/13/18.
 */

class AddEditTaskFragment: Fragment(), AddEditTasksContract.View{

    override  lateinit var presenter: AddEditTasksContract.Presenter

    private lateinit var title: TextView
    private lateinit var description: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.addtask_frag, container, false)
        with(root){
            title = findViewById(R.id.add_task_title)
            description = findViewById(R.id.add_task_description)
        }
        setHasOptionsMenu(true)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(activity.findViewById<FloatingActionButton>(R.id.fab_edit_task_done)){
            setImageResource(R.drawable.ic_done)
            setOnClickListener { presenter.saveTask(title.text.toString(), description.text.toString()) }
        }
    }

    override fun showEmptyTaskError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskList() {
        with(activity){
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        const val ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID"

        fun newInstance(taskId: String?) = AddEditTaskFragment().apply {
            arguments = Bundle().apply {
                putString(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID, taskId)
            }
        }
    }
}