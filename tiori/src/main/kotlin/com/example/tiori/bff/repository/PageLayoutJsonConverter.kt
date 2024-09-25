package com.example.tiori.bff.repository

import com.example.tiori.bff.model.PageLayoutContainer
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.AttributeConverter

class PageLayoutJsonConverter : AttributeConverter<PageLayoutContainer, String> {
    override fun convertToDatabaseColumn(p: PageLayoutContainer): String {
        return mapper.writeValueAsString(p)
    }

    override fun convertToEntityAttribute(s: String): PageLayoutContainer {
        return mapper.readValue(s, PageLayoutContainer::class.java)
    }

    companion object {
        private val mapper = jacksonObjectMapper()
            .registerModules(JavaTimeModule())
            .also {
                it.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
            }
    }
}