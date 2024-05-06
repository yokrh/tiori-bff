package com.example.tiori.bff.repository.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "page")
data class PageEntity (
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name="shiori_id")
    val shioriId: Long,

    @Column(name="layout_json")
    val layoutJson: String,

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
        fun dummy(): PageEntity = PageEntity(
            shioriId = -1,
            layoutJson = "dummy",
        )
    }
}
