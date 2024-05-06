package com.example.tiori.bff.repository.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "shiori")
data class ShioriEntity (
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name="customer_id")
    val customerId: Long,

    @Column(name="title")
    val title: String,

    @Column(name="created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name="updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Version
    @Column(name="version")
    val version: Int = 0,

    @Column(name="is_deleted")
    val isDeleted: Boolean = false,
) {
    companion object {
        fun dummy(): ShioriEntity = ShioriEntity(
            customerId = -1,
            title = "dummy",
        )
    }
}
