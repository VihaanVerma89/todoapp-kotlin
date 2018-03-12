package com.example.android.architecture.blueprints.todoapp.data

import android.arch.persistence.room.Entity
import java.util.*

/**
 * Created by vihaanverma on 01/03/18.
 */

@Entity(tableName = "tasks")
data class Task @JvmOverloads constructor(
        var title: String = "",
        var description: String = "",
        var id: String = UUID.randomUUID().toString()
)
{
    var isCompleted = false

    val titleForList: String
        get() = if (title.isNotEmpty()) title else description

    val isActive
    get() = !isCompleted

    val isEmpty
    get()=title.isEmpty() && description.isEmpty()
}