package com.example.tiori.bff.repository

import com.example.tiori.bff.repository.entity.ShioriEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShioriRepository: CrudRepository<ShioriEntity, Long> {
    fun findByIdAndIsDeletedFalse(shioriId: Long): ShioriEntity?
    fun findAllByCustomerIdAndIsDeletedFalse(customerId: Long): List<ShioriEntity>
}