package com.goofy.infratest.exception

import org.springframework.validation.BindingResult
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

class ErrorResponse private constructor(
    var errorCode: String,
    var reason: String,
    var errors: List<FieldError> = listOf()
) {
    constructor(code: ErrorCode, message: String) : this(reason = message, errorCode = code.name)

    constructor(code: ErrorCode, e: BusinessException) : this(code, e.message ?: code.message) {
        errors = FieldError.ofEmptyList()
    }

    class FieldError private constructor(
        val field: String,
        val value: String,
        val reason: String
    ) {
        companion object {
            fun ofList(bindingResult: BindingResult): List<FieldError> {
                return bindingResult.fieldErrors.map {
                    FieldError(
                        it.field, when (it.rejectedValue) {
                            is String -> it.rejectedValue as String
                            else -> ""
                        }, it.defaultMessage ?: ""
                    )
                }
            }

            fun ofList(e: MethodArgumentTypeMismatchException): List<FieldError> {
                return listOf(
                    FieldError(
                        field = e.name,
                        value = e.value as String? ?: "",
                        reason = e.errorCode
                    )
                )
            }

            fun ofList(e: Exception): List<FieldError> {
                return listOf(
                    FieldError(
                        field = "",
                        value = "",
                        reason = e.message ?: "error message is empty"
                    )
                )
            }

            fun ofEmptyList(): List<FieldError> {
                return emptyList()
            }
        }
    }
}
