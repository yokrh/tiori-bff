package com.example.tiori.bff.service

import com.example.tiori.bff.controller.request.CreatePageRequest
import com.example.tiori.bff.controller.request.UpdatePageRequest
import com.example.tiori.bff.controller.response.CreatePageResponseBody
import com.example.tiori.bff.controller.response.GetPageResponseBody
import com.example.tiori.bff.model.BlockType
import org.springframework.stereotype.Service

@Service
class PageServiceManager(
    private val pageService: PageService,
    private val blockService: BlockService,
) {
    fun getPage(pageId: Long): GetPageResponseBody {
        pageService.getPage(pageId = pageId)
            .let {
                val blockList = blockService.getBlockList(pageId = it.id)
                return GetPageResponseBody(
                    page = GetPageResponseBody.Page(
                        id = it.id,
                        layout = it.layoutJson,
                        blockList = blockList.map { block ->
                            GetPageResponseBody.Block(
                                id = block.id,
                                type = BlockType.of(block.type),
                                content = block.contentJson
                            )
                        }
                    )
                )
            }
    }

    fun createPage(request: CreatePageRequest): CreatePageResponseBody {
        val pageId = pageService.createPage(request = request)
        return CreatePageResponseBody(pageId = pageId)
    }

    fun updatePage(
        pageId: Long,
        request: UpdatePageRequest,
    ) {
        pageService.updatePage(
            pageId = pageId,
            request = request,
        )
    }

    fun deletePage(pageId: Long) {
        pageService.deletePage(pageId = pageId)
    }
}
