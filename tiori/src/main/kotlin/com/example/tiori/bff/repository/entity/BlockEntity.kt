package com.example.tiori.bff.repository.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "block")
data class BlockEntity (
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name="page_id")
    val pageId: Long,

    @Column(name="type")
    val type: String,

    @Column(name="content_json")
    val contentJson: String,

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
        fun dummy(): BlockEntity = BlockEntity(
            pageId = -1,
            type = "dummy",
            contentJson = "dummy",
        )
    }
}
