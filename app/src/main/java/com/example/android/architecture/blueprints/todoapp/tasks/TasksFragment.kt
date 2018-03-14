package com.example.android.architecture.blueprints.todoapp.tasks

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.*
import android.widget.*
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskActivity
import com.example.android.architecture.blueprints.todoapp.data.Task

/**
 * Created by vihaanverma on 01/03/18.
 */

class TasksFragment : Fragment(), TasksContract.View {

    override var isActive: Boolean = false
        get() = isAdded

    private lateinit var noTaskView: View
    private lateinit var noTaskIcon: ImageView
    private lateinit var noTaskMainView: TextView
    private lateinit var noTaskAddView: TextView
    private lateinit var taskView: LinearLayout
    private lateinit var filteringLableView: TextView


    override fun setLoadingIndicator(active: Boolean) {

    }

    override lateinit var presenter: TasksContract.Presenter

    internal var itemListener: TaskItemListener = object : TaskItemListener {
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

    private val listAdapter = TasksAdapter(ArrayList(0), itemListener)


    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater?.inflate(R.layout.tasks_frag, container, false)
        with(root) {
            this!!
            val listview = findViewById<ListView>(R.id.tasks_list)?.apply {
                adapter = listAdapter
            }

            findViewById<ScrollChildSwipeRefreshLayout>(R.id.refresh_layout).apply {
                setColorSchemeColors(
                        ContextCompat.getColor(activity, R.color.colorPrimary),
                        ContextCompat.getColor(activity, R.color.colorAccent),
                        ContextCompat.getColor(activity, R.color.colorPrimaryDark)
                )
               scrollUpChild = listview
                setOnRefreshListener { presenter.loadTasks(false) }
            }

            filteringLableView = findViewById(R.id.filteringLabel)
            taskView = findViewById(R.id.tasksLL)
            noTaskView = findViewById(R.id.noTasks)
            noTaskIcon = findViewById(R.id.noTasksIcon)
            noTaskMainView = findViewById(R.id.noTasksMain)
            noTaskAddView = findViewById<TextView>(R.id.noTasksAdd).also {
                it.setOnClickListener { showAddTask() }
            }

        }

        activity.findViewById<FloatingActionButton>(R.id.fab_add_task).apply {
            setImageResource(R.drawable.ic_add)
            setOnClickListener { presenter.addNewTask() }
        }

        setHasOptionsMenu(true)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.tasks_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_clear -> presenter.clearCompletedTasks()
            R.id.menu_filter -> showFilteringPopupMenu()
            R.id.menu_refresh -> presenter.loadTasks(true)
        }
        return true
    }

    private fun showFilteringPopupMenu() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddTask() {
        val intent = Intent(context, AddEditTaskActivity::class.java)
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK)
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
                    if (!task.isCompleted) {
                        itemListener.onCompleteTaskClick(task)
                    } else {
                        itemListener.onActivateTaskClick(task)
                    }
                }
            }
            rowView.setOnClickListener { itemListener.onTaskClick(task) }
            return rowView
        }

    }

    override fun showNoActiveTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoCompletedTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingTasksError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTasks(tasks: List<Task>) {
        listAdapter.tasks = tasks
    }


}