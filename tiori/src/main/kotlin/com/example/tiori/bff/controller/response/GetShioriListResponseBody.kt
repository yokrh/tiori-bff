package com.example.tiori.bff.controller.response

data class GetShioriListResponseBody(
    val shioriList: List<Shiori>,
) : ApiResponse.Body {

    data class Shiori(
        val id: Long,
        val title: String,
    )
}
