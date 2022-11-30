package com.goofy.infratest.controller

import com.goofy.infratest.dto.TodoRequest
import com.goofy.infratest.dto.wrapPage
import com.goofy.infratest.dto.wrapResponse
import com.goofy.infratest.service.TodoService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/todo", produces = [APPLICATION_JSON_VALUE])
class TodoController(
    private val todoService: TodoService
) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = todoService.findById(id).wrapResponse()

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 20, sort = ["createdAt"]) pageable: Pageable) =
        todoService.findAll(pageable).wrapPage()

    @PostMapping
    fun insert(@RequestBody request: TodoRequest) = todoService.insert(request).wrapResponse()
}
