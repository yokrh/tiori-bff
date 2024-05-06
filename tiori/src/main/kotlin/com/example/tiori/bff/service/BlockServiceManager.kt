package com.example.tiori.bff.service

import com.example.tiori.bff.controller.request.CreateBlockRequest
import com.example.tiori.bff.controller.request.UpdateBlockRequest
import com.example.tiori.bff.controller.response.CreateBlockResponseBody
import org.springframework.stereotype.Service

@Service
class BlockServiceManager(
    private val blockService: BlockService,
) {
    fun createBlock(request: CreateBlockRequest): CreateBlockResponseBody {
        val blockId = blockService.createBlock(request = request)
        return CreateBlockResponseBody(blockId = blockId)
    }

    fun updateBlock(
        blockId: Long,
        request: UpdateBlockRequest,
    ) {
        blockService.updateBlock(
            blockId = blockId,
            request = request,
        )
    }

    fun deleteBlock(blockId: Long) {
        blockService.deleteBlock(blockId = blockId)
    }
}
