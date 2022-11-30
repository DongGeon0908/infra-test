package com.goofy.infratest.dto

import com.goofy.infratest.entity.vo.TodoCategory

data class TodoRequest(
    val title: String,
    val content: String,
    val category: TodoCategory
)
