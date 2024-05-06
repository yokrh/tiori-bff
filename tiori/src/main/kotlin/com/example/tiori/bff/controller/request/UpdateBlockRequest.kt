package com.example.tiori.bff.controller.request

import com.example.tiori.bff.model.BlockType
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateBlockRequest(
    @JsonProperty("type")
    var type: BlockType,

    @JsonProperty("content")
    @field:NotBlank(message = "content is required")
    @field:Size(max = 1000, message = "max length of content is 1000")
    var content: String,
)
