package com.goofy.infratest.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.goofy.infratest.entity.Todo
import com.goofy.infratest.entity.vo.TodoCategory
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val category: TodoCategory,
    val isActive: Boolean,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    val createdAt: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    val modifiedAt: LocalDateTime
) {
    constructor(todo: Todo) : this(
        id = todo.id,
        title = todo.title,
        content = todo.content,
        category = todo.category,
        isActive = todo.active,
        createdAt = todo.createdAt,
        modifiedAt = todo.modifiedAt
    )
}
