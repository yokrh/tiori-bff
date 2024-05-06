package com.example.tiori.bff.repository

import com.example.tiori.bff.repository.entity.PageEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PageRepository: CrudRepository<PageEntity, Long> {
    fun findByIdAndIsDeletedFalse(pageId: Long): PageEntity?
    fun findAllByShioriId(shioriId: Long): List<PageEntity>
}