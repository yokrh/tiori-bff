package com.example.tiori.bff.repository.entity

import com.example.tiori.bff.model.PageLayoutContainer
import com.example.tiori.bff.repository.PageLayoutJsonConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Version
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

    @Convert(converter = PageLayoutJsonConverter::class)
    @Column(name="layout_json")
    val layoutJson: PageLayoutContainer,

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
            layoutJson = PageLayoutContainer(
                list = listOf()
            ),
        )
    }
}
