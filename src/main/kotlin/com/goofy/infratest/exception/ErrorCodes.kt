package com.goofy.infratest.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    TODO_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "not found todo"),
    ;
}
