package com.example.tiori.bff.controller.response

data class CreateUserResponseBody(
    val name: String,
    val uid: String,
) : ApiResponse.Body
