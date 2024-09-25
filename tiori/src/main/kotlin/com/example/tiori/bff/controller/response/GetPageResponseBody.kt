package com.example.tiori.bff.controller.response

import com.example.tiori.bff.model.BlockType
import com.example.tiori.bff.model.PageLayoutContainer

data class GetPageResponseBody(
    val page: Page,
) : ApiResponse.Body {

    data class Page(
        val id: Long,
        val layout: PageLayoutContainer,
        val blockList: List<Block>
    )

    data class Block(
        val id: Long,
        val type: BlockType,
        val content: String
    )
}
