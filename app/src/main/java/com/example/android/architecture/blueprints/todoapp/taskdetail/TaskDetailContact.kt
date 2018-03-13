package com.example.android.architecture.blueprints.todoapp.taskdetail

import com.example.android.architecture.blueprints.todoapp.tasks.BasePresenter
import com.example.android.architecture.blueprints.todoapp.tasks.BaseView

/**
 * Created by vihaan on 3/13/18.
 */

interface TaskDetailContact {

    interface View : BaseView<Presenter>
    {

    }

    interface Presenter: BasePresenter{

    }
}