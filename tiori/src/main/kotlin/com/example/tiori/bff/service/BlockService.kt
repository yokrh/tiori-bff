package com.example.tiori.bff.service

import com.example.tiori.bff.controller.request.CreateBlockRequest
import com.example.tiori.bff.controller.request.UpdateBlockRequest
import com.example.tiori.bff.controller.exception.CommonErrorException
import com.example.tiori.bff.controller.response.GetShioriResponseBody
import com.example.tiori.bff.repository.entity.BlockEntity
import com.example.tiori.bff.repository.BlockRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BlockService(
    private val blockRepository: BlockRepository,
) {
    fun getBlockList(pageId: Long): List<BlockEntity> {
        return blockRepository.findAllByPageIdAndIsDeletedFalse(pageId)
    }

    fun createBlock(request: CreateBlockRequest): Long {
        return blockRepository.save(
            BlockEntity(
                pageId = request.pageId,
                type = request.type.name,
                contentJson = request.content
            )
        ).id
    }

    fun updateBlock(
        blockId: Long,
        request: UpdateBlockRequest,
    ) {
        val entity = blockRepository.findByIdAndIsDeletedFalse(blockId)
            ?: throw CommonErrorException(HttpStatus.BAD_REQUEST, "block not found")

        blockRepository.save(
            entity.copy(
                type = request.type.name,
                contentJson = request.content,
            )
        )
    }

    fun deleteBlock(blockId: Long) {
        val entity = blockRepository.findById(blockId).orElse(null)
            ?: throw CommonErrorException(HttpStatus.BAD_REQUEST, "block not found")

        blockRepository.save(
            entity.copy(isDeleted = true)
        )
    }

    fun loadBlock(
        pageId: Long,
        block: GetShioriResponseBody.Block,
    ) {
        val maxId = blockRepository.count()
        val recordLackLength = (block.id - maxId - 1).toInt()
        if (recordLackLength > 0) {
            blockRepository.saveAll(
                Array(recordLackLength) { BlockEntity.dummy() }.asList()
            )
        }

        blockRepository.save(BlockEntity(
            id = block.id,
            pageId = pageId,
            type = block.type.name,
            contentJson = block.content
        ))
    }
}
