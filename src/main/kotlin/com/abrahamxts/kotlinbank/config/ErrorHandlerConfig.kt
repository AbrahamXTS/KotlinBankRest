package com.abrahamxts.kotlinbank.config

import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorHandlerConfig : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun generalErrorHandler(e: Exception, request: WebRequest): ResponseEntity<String> {
        return ResponseEntity<String>("Ops! Algo salió mal: ${e.message}", HttpStatus.BAD_REQUEST)
    }
}