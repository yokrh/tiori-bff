package com.example.tiori.bff.repository

import com.example.tiori.bff.repository.entity.BlockEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BlockRepository: CrudRepository<BlockEntity, Long> {
    fun findByIdAndIsDeletedFalse(blockId: Long): BlockEntity?
    fun findAllByPageIdAndIsDeletedFalse(pageId: Long): List<BlockEntity>
}