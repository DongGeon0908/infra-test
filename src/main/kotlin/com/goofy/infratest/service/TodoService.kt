package com.goofy.infratest.service

import com.goofy.infratest.dto.TodoRequest
import com.goofy.infratest.dto.TodoResponse
import com.goofy.infratest.entity.Todo
import com.goofy.infratest.exception.BusinessException
import com.goofy.infratest.exception.ErrorCode
import com.goofy.infratest.repository.TodoRepository
import mu.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {
    private val logger = KotlinLogging.logger {}

    fun findById(id: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(id) ?: throw BusinessException(ErrorCode.TODO_NOT_FOUND_ERROR)
        return TodoResponse(todo)
    }

    fun findAll(pageable: Pageable) = todoRepository.findAll(pageable).map { TodoResponse(it) }

    fun insert(request: TodoRequest): TodoResponse {
        val todo = todoRepository.save(
            Todo(
                title = request.title,
                content = request.content,
                category = request.category
            )
        )

        logger.info { "insert todo list : todoID : ${todo.id}" }

        return TodoResponse(todo)
    }
}
