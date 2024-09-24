package com.example.tiori.bff.repository

import com.example.tiori.bff.repository.entity.CustomerEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: CrudRepository<CustomerEntity, Long> {
    fun findByUidAndIsDeletedFalse(uid: String): CustomerEntity?
    fun findByGid(gid: String): CustomerEntity?
}