package com.example.tiori.bff.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdatePageRequest(
    @JsonProperty("layout")
    @field:NotBlank(message = "layout is required")
    @field:Size(max = 1000, message = "max length of layout is 1000")
    var layout: String,
)
