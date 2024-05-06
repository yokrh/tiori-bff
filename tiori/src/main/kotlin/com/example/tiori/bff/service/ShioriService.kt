package com.example.tiori.bff.service

import com.example.tiori.bff.controller.request.CreateShioriRequest
import com.example.tiori.bff.controller.request.UpdateShioriRequest
import com.example.tiori.bff.controller.exception.CommonErrorException
import com.example.tiori.bff.controller.response.GetShioriResponseBody
import com.example.tiori.bff.repository.ShioriRepository
import com.example.tiori.bff.repository.entity.ShioriEntity
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ShioriService(
    private val shioriRepository: ShioriRepository,

) {
    fun getShioriList(customerId: Long): List<ShioriEntity> {
        return shioriRepository.findAllByCustomerIdAndIsDeletedFalse(customerId = customerId)
    }

    fun getShiori(
        shioriId: Long
    ): ShioriEntity = shioriRepository.findByIdAndIsDeletedFalse(shioriId)
        ?: throw CommonErrorException(HttpStatus.BAD_REQUEST, "shiori not found")

    fun createShiori(
        customerId: Long,
        request: CreateShioriRequest
    ): Long {
        return shioriRepository.save(
            ShioriEntity(
                customerId = customerId,
                title = request.title
            )
        ).id
    }

    fun updateShiori(
        shioriId: Long,
        request: UpdateShioriRequest,
    ) {
        val entity = shioriRepository.findByIdAndIsDeletedFalse(shioriId)
            ?: throw CommonErrorException(HttpStatus.BAD_REQUEST, "shiori not found")

        shioriRepository.save(
            entity.copy(
                title = request.title,
            )
        )
    }

    fun deleteShiori(shioriId: Long) {
        val entity = shioriRepository.findById(shioriId).orElse(null)
            ?: throw CommonErrorException(HttpStatus.BAD_REQUEST, "shiori not found")

        shioriRepository.save(
            entity.copy(isDeleted = true)
        )
    }

    fun loadShiori(
        customerId: Long,
        shiori: GetShioriResponseBody.Shiori,
    ) {
        val maxId = shioriRepository.count()
        val recordLackLength = (shiori.id - maxId - 1).toInt()
        if (recordLackLength > 0) {
            shioriRepository.saveAll(
                Array(recordLackLength) { ShioriEntity.dummy() }.asList()
            )
        }

        shioriRepository.save(ShioriEntity(
            id = shiori.id,
            customerId = customerId,
            title = shiori.title,
        ))
    }
}
