package com.example.tiori.bff.service

import com.example.tiori.bff.controller.request.CreateShioriRequest
import com.example.tiori.bff.controller.request.LoadShioriRequest
import com.example.tiori.bff.controller.request.UpdateShioriRequest
import com.example.tiori.bff.controller.response.CreateShioriResponseBody
import com.example.tiori.bff.controller.response.GetShioriListResponseBody
import com.example.tiori.bff.controller.response.GetShioriResponseBody
import com.example.tiori.bff.model.BlockType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ShioriServiceManager(
    private val shioriService: ShioriService,
    private val pageService: PageService,
    private val blockService: BlockService,
) {
    fun getShioriList(customerId: Long): GetShioriListResponseBody {
        shioriService.getShioriList(customerId = customerId)
            .let {
                return GetShioriListResponseBody(
                    it.map { shiori ->
                        GetShioriListResponseBody.Shiori(
                            id = shiori.id,
                            title = shiori.title
                        )
                    }
                )
            }
    }

    fun getShiori(
        customerId: Long,
        shioriId: Long,
    ): GetShioriResponseBody {
        shioriService.getShiori(shioriId = shioriId)
            .let { shiori ->
                val pageList = pageService.getPageList(shioriId = shioriId)
                    .map { page ->
                        val blockList = blockService.getBlockList(pageId = page.id)
                        GetShioriResponseBody.Page(
                            id = page.id,
                            layout = page.layoutJson,
                            blockList = blockList.map { block ->
                                GetShioriResponseBody.Block(
                                    id = block.id,
                                    type = BlockType.of(block.type),
                                    content = block.contentJson
                                )
                            }
                        )
                    }
                return GetShioriResponseBody(
                    shiori = GetShioriResponseBody.Shiori(
                        id = shiori.id,
                        title = shiori.title,
                        pageList = pageList
                    )
                )
            }
    }

    fun createShiori(
        customerId: Long,
        request: CreateShioriRequest
    ): CreateShioriResponseBody {
        val shioriId = shioriService.createShiori(
            customerId = customerId,
            request = request
        )
        return CreateShioriResponseBody(shioriId = shioriId)
    }

    fun updateShiori(
        shioriId: Long,
        request: UpdateShioriRequest,
    ) {
        shioriService.updateShiori(
            shioriId = shioriId,
            request = request
        )
    }

    fun deleteShiori(shioriId: Long) {
        shioriService.deleteShiori(shioriId = shioriId)
    }

    @Transactional
    fun loadShiori(
        customerId: Long,
        request: LoadShioriRequest,
    ) {
        request.shiori.let { shiori ->
            shioriService.loadShiori(
                customerId = customerId,
                shiori = shiori,
            )

            shiori.pageList.forEach { page ->
                pageService.loadPage(
                    shioriId = shiori.id,
                    page = page,
                )

                page.blockList.forEach { block ->
                    blockService.loadBlock(
                        pageId = page.id,
                        block = block
                    )
                }
            }
        }
    }
}
