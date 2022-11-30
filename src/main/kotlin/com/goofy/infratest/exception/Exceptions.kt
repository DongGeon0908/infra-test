package com.goofy.infratest.exception

open class BusinessException(
    val code: ErrorCode,
    override val message: String? = code.message
) : RuntimeException(
    message ?: code.message
)
