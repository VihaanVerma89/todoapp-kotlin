package com.example.android.architecture.blueprints.todoapp.tasks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.ScrollChildSwipeRefreshLayout
import com.example.android.architecture.blueprints.todoapp.data.Task

/**
 * Created by vihaanverma on 01/03/18.
 */

class TasksFragment : Fragment(), TasksContract.View {
    override lateinit var presenter: TasksContract.Presenter

    internal var itemListener: TaskItemListener = object :TaskItemListener{
        override fun onCompleteTaskClick(compltedTask: Task) {
            presenter.completeTask(compltedTask)
        }

        override fun onActivateTaskClick(activatedTask: Task) {
            presenter.activateTask(activatedTask)
        }

        override fun onTaskClick(clickedTask: Task) {
            presenter.openTaskDetails(clickedTask)
        }
    }

    private val listAdapter = TasksAdapter(ArrayList(0), itemListener )


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater?.inflate(R.layout.tasks_frag, container, false)
        with(root) {
            val listview = this?.findViewById<ListView>(R.id.tasks_list)?.apply {
                adapter = listAdapter
            }

            findViewById<ScrollChildSwipeRefreshLayout>(R.id.refresh_layout).apply{

            }
        }
        return root
    }

    companion object {
        fun newInstance() = TasksFragment()
    }

    interface TaskItemListener {
        fun onTaskClick(clickedTask: Task)
        fun onCompleteTaskClick(compltedTask: Task)
        fun onActivateTaskClick(activatedTask: Task)
    }

    private class TasksAdapter(tasks: List<Task>, private val itemListener: TaskItemListener)
        : BaseAdapter() {

        var tasks: List<Task> = tasks
            set(tasks) {
                field = tasks
                notifyDataSetChanged()
            }

        override fun getItem(position: Int): Task {
            return tasks[position];
        }

        override fun getItemId(position: Int): Long {
            return position.toLong();
        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val task = getItem(position)
            val rowView = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout
                    .task_item,
                    parent, false)

            with(rowView.findViewById<TextView>(R.id.title))
            {
                text = task.titleForList
            }

            with(rowView.findViewById<CheckBox>(R.id.complete))
            {
                isChecked = task.isCompleted
                val rowViewBackground = if (task.isCompleted) R.drawable.list_completed_touch_feedback
                else
                    R.drawable.touch_feedback

                rowView.setBackgroundResource(rowViewBackground)
                setOnClickListener {
                    if(!task.isCompleted)
                    {
                        itemListener.onCompleteTaskClick(task)
                    }else{
                        itemListener.onActivateTaskClick(task)
                    }
                }
            }
            rowView.setOnClickListener { itemListener.onTaskClick(task) }
            return rowView
        }

    }
}