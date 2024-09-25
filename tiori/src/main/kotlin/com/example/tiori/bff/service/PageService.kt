package com.example.tiori.bff.service

import com.example.tiori.bff.controller.request.CreatePageRequest
import com.example.tiori.bff.controller.request.UpdatePageRequest
import com.example.tiori.bff.controller.exception.CommonErrorException
import com.example.tiori.bff.controller.response.GetShioriResponseBody
import com.example.tiori.bff.model.PageLayout
import com.example.tiori.bff.model.PageLayoutContainer
import com.example.tiori.bff.repository.PageRepository
import com.example.tiori.bff.repository.entity.PageEntity
import org.hibernate.query.Page
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class PageService(
    private val pageRepository: PageRepository,
) {
    fun getPageList(shioriId: Long): List<PageEntity> {
        return pageRepository.findAllByShioriId(shioriId = shioriId)
    }

    fun getPage(pageId: Long): PageEntity {
        return pageRepository.findByIdAndIsDeletedFalse(pageId)
            ?: throw CommonErrorException(HttpStatus.BAD_REQUEST, "page not found")
    }

    fun createPage(request: CreatePageRequest): Long {
        return pageRepository.save(
            PageEntity(
                shioriId = request.shioriId,
                layoutJson = PageLayoutContainer(
                    list = request.layout.pageLayoutList.map {
                        PageLayout(
                            type = it.type,
                            blockIdList = it.blockIdList,
                        )
                    }
                )
            )
        ).id
    }

    fun updatePage(
        pageId: Long,
        request: UpdatePageRequest,
    ) {
        val entity = pageRepository.findByIdAndIsDeletedFalse(pageId)
            ?: throw CommonErrorException(HttpStatus.BAD_REQUEST, "page not found")

        pageRepository.save(
            entity.copy(
                layoutJson = PageLayoutContainer(
                    list = request.layout.pageLayoutList.map {
                        PageLayout(
                            type = it.type,
                            blockIdList = it.blockIdList,
                        )
                    }
                )
            )
        )
    }

    fun deletePage(pageId: Long) {
        val entity = pageRepository.findById(pageId).orElse(null)
            ?: throw CommonErrorException(HttpStatus.BAD_REQUEST, "page not found")

        pageRepository.save(
            entity.copy(isDeleted = true)
        )
    }

    fun loadPage(
        shioriId: Long,
        page: GetShioriResponseBody.Page
    ) {
        val maxId = pageRepository.count()
        val recordLackLength = (page.id - maxId - 1).toInt()
        if (recordLackLength > 0) {
            pageRepository.saveAll(
                Array(recordLackLength) { PageEntity.dummy() }.asList()
            )
        }

        pageRepository.save(
            PageEntity(
                id = page.id,
                shioriId = shioriId,
                layoutJson = PageLayoutContainer(
                    list = page.layout.list.map {
                        PageLayout(
                            type = it.type,
                            blockIdList = it.blockIdList,
                        )
                    }
                )
            )
        )
    }
}
