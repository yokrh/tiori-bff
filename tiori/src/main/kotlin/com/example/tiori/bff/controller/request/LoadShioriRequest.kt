package com.example.tiori.bff.controller.request

import com.example.tiori.bff.controller.response.GetShioriResponseBody
import com.fasterxml.jackson.annotation.JsonProperty

data class LoadShioriRequest(
    @JsonProperty("shiori")
    val shiori: GetShioriResponseBody.Shiori
)
