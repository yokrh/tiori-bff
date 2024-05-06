package com.example.tiori.bff.service

import com.example.tiori.bff.controller.exception.CommonErrorException
import com.example.tiori.bff.controller.request.CreateUserRequest
import com.example.tiori.bff.controller.response.CreateUserResponseBody
import com.example.tiori.bff.repository.CustomerRepository
import com.example.tiori.bff.repository.entity.CustomerEntity
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
) {
    fun auth(uid: String): Long {
        // TODO: cache

        val entity = customerRepository.findByUidAndIsDeletedFalse(uid)
            ?: throw CommonErrorException(HttpStatus.NOT_FOUND, "customer not found")
        return entity.id
    }

    fun save(request: CreateUserRequest): CreateUserResponseBody {
        val uid = UUID.randomUUID().toString()

        val entity = customerRepository.save(CustomerEntity(
            uid = uid,
            name = request.name,
        ))

        return CreateUserResponseBody(
            name = entity.name,
            uid = entity.uid,
        )
    }
}
