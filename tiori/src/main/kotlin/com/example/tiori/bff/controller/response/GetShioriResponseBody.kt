package com.example.tiori.bff.controller.response

import com.example.tiori.bff.model.BlockType

data class GetShioriResponseBody(
    val shiori: Shiori,
) : ApiResponse.Body {

    data class Shiori(
        val id: Long,
        val title: String,
        val pageList: List<Page>
    )

    data class Page(
        val id: Long,
        val layout: String,
        val blockList: List<Block>
    )

    data class Block(
        val id: Long,
        val type: BlockType,
        val content: String
    )
}
