package com.example.tiori.bff.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateUserRequest(
    @JsonProperty("name")
    @field:NotBlank(message = "name is required")
    @field:Size(max = 32, message = "max length of name is 32")
    var name: String,
)
