package com.example.tiori.bff.controller.exception

import org.springframework.http.HttpStatus

class CommonErrorException(
    val httpStatus: HttpStatus,
    val errorMessage: String,
) : RuntimeException(errorMessage)
