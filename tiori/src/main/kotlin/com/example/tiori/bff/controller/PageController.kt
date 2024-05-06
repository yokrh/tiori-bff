package com.example.tiori.bff.controller

import com.example.tiori.bff.controller.request.CommonHeader
import com.example.tiori.bff.controller.request.CreatePageRequest
import com.example.tiori.bff.controller.request.UpdatePageRequest
import com.example.tiori.bff.controller.response.ApiResponse
import com.example.tiori.bff.controller.response.CreatePageResponseBody
import com.example.tiori.bff.controller.response.GetPageResponseBody
import com.example.tiori.bff.service.CustomerService
import com.example.tiori.bff.service.PageServiceManager
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/v1/page")
class PageController(
    private val pageServiceManager: PageServiceManager,
    private val customerService: CustomerService,
) {
    @GetMapping("/{pageId}")
    fun getPage(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @PathVariable("pageId") pageId: Long,
    ): ResponseEntity<ApiResponse<GetPageResponseBody>> {
        customerService.auth(uid = uid)

        val response = pageServiceManager.getPage(pageId = pageId)
        return ResponseEntity.ok(ApiResponse.success(response))
    }

    @PostMapping
    fun createPage(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @RequestBody @Valid request: CreatePageRequest,
    ): ResponseEntity<ApiResponse<CreatePageResponseBody>> {
        customerService.auth(uid = uid)

        val response = pageServiceManager.createPage(request)
        return ResponseEntity.ok(ApiResponse.success(response))
    }


    @PutMapping("/{pageId}")
    fun updateBlock(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @PathVariable("pageId") pageId: Long,
        @RequestBody @Valid request: UpdatePageRequest,
    ): ResponseEntity<ApiResponse<ApiResponse.EmptyBody>> {
        customerService.auth(uid = uid)

        pageServiceManager.updatePage(
            pageId = pageId,
            request = request,
        )
        return ResponseEntity.ok(ApiResponse.success())
    }

    @DeleteMapping("/{pageId}")
    fun deletePage(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @PathVariable("pageId") pageId: Long,
    ): ResponseEntity<ApiResponse<ApiResponse.EmptyBody>> {
        customerService.auth(uid = uid)

        pageServiceManager.deletePage(pageId = pageId)
        return ResponseEntity.ok(ApiResponse.success())
    }
}
