package com.example.tiori.bff.model

import com.example.tiori.bff.controller.exception.CommonErrorException
import org.springframework.http.HttpStatus

enum class BlockType {
    TITLE,
    SCHEDULE,
    PARTICIPANT,
    SPOT,
    MOVE,
    TEXT,
    IMAGE,
    TEXT_WITH_IMAGE_TOP_LEFT,
    TEXT_WITH_IMAGE_TOP_RIGHT,
    TEXT_WITH_IMAGE_BOTTOM_LEFT,
    TEXT_WITH_IMAGE_BOTTOM_RIGHT,
    ;

    companion object {
        fun of(str: String): BlockType {
            return values()
                .firstOrNull { it.name == str }
                ?: throw CommonErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "invalid block type")
        }
    }
}