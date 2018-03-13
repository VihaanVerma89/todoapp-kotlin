package com.example.android.architecture.blueprints.todoapp.taskdetail

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
class TaskDetailFragment: Fragment(), TaskDetailContact.View{

    override lateinit var presenter: TaskDetailContact.Presenter

    private lateinit var detailTitle: TextView
    private lateinit var detailDescription: TextView
    private lateinit var detailCompleteStatus: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.taskdetail_frag, container, false);

        setHasOptionsMenu(true)
        with(root){
            detailTitle = this?.findViewById(R.id.task_detail_title)!!
            detailDescription= this?.findViewById(R.id.task_detail_description)!!
            detailCompleteStatus= this?.findViewById(R.id.task_detail_complete)!!
        }

        activity.findViewById<FloatingActionButton>(R.id.fab_edit_task)

        return root;
    }

    companion object {
        private val ARGUMENT_TASK_ID = "TASK_ID"
        private val REQUEST_EDIT_TASK = 1

        fun getInstance(taskId: String?) = TaskDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_TASK_ID, taskId)
            }
        }
    }

}