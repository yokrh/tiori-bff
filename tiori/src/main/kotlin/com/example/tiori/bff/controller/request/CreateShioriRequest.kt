package com.example.tiori.bff.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateShioriRequest(
    @JsonProperty("title")
    @field:NotBlank(message = "title is required")
    @field:Size(max = 32, message = "max length of title is 32")
    var title: String,
)
