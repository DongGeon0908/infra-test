package com.goofy.infratest.exception

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = KotlinLogging.logger { }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<*> {
        logger.error("BusinessException ${e.message}")
        val response = ErrorResponse(e.code, e)
        return ResponseEntity(response, e.code.status)
    }
}
