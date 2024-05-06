package com.example.tiori.bff.controller.exception

import com.example.tiori.bff.controller.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CommonExceptionHandler {
    @ExceptionHandler(CommonErrorException::class)
    fun handle(exception: CommonErrorException): ResponseEntity<*> {
        return ResponseEntity
            .status(exception.httpStatus)
            .body(ApiResponse.error(message = exception.errorMessage))
    }
}
