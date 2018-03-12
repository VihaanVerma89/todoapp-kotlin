package com.example.android.architecture.blueprints.todoapp.tasks

/**
 * Created by vihaanverma on 01/03/18.
 */
interface BaseView<T>{

    var presenter: T
}