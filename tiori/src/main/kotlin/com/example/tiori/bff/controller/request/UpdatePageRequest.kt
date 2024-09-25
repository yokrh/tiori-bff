package com.example.tiori.bff.controller.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdatePageRequest(
    @JsonProperty("layout")
    var layout: CreatePageRequest.PageLayoutContainer,
)
