package com.example.android.architecture.blueprints.todoapp.tasks

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import com.example.android.architecture.blueprints.todoapp.Injection
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.util.replaceFragmentInActivity
import com.example.android.architecture.blueprints.todoapp.util.setupActionBar

/**
 * Created by vihaanverma on 27/02/18.
 */

class TasksActivity: AppCompatActivity(){

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var tasksPresenter: TasksPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)
        setupActionBar(R.id.toolbar){
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }

        setupDrawerContent(findViewById(R.id.nav_view))

        val tasksFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        as TasksFragment? ?: TasksFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        tasksPresenter = TasksPresenter(Injection.provideTasksRepository(applicationContext),
                tasksFragment).apply {

        }
    }

    private fun setupDrawerContent(navigationView: NavigationView)
    {
        navigationView.setNavigationItemSelectedListener {
            menuItem ->
            if(menuItem.itemId == R.id.statistics_navigation_menu_item){
//                val intent = Intent(this@TasksActivity, )

            }

            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

    }
}