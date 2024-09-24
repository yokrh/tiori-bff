package com.example.tiori.bff.repository.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "customer")
data class CustomerEntity (
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name="uid")
    val uid: String,

    @Column(name="name")
    val name: String,

    @Column(name="gid")
    val gid: String,

    @Column(name="gname")
    val gname: String,

    @Column(name="gmail")
    val gmail: String,

    @Column(name="created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name="updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Version
    @Column(name="version")
    val version: Int = 0,

    @Column(name="is_deleted")
    val isDeleted: Boolean = false,
)
