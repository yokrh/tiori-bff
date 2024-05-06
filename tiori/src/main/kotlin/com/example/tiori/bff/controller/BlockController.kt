package com.example.tiori.bff.controller

import com.example.tiori.bff.controller.request.CommonHeader
import com.example.tiori.bff.controller.request.CreateBlockRequest
import com.example.tiori.bff.controller.request.UpdateBlockRequest
import com.example.tiori.bff.controller.response.ApiResponse
import com.example.tiori.bff.controller.response.CreateBlockResponseBody
import com.example.tiori.bff.service.BlockServiceManager
import com.example.tiori.bff.service.CustomerService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/v1/block")
class BlockController(
    private val blockServiceManager: BlockServiceManager,
    private val customerService: CustomerService,
) {
    @PostMapping
    fun createBlock(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @RequestBody @Valid request: CreateBlockRequest,
    ): ResponseEntity<ApiResponse<CreateBlockResponseBody>> {
        customerService.auth(uid = uid)

        val response = blockServiceManager.createBlock(request)
        return ResponseEntity.ok(ApiResponse.success(response))
    }

    @PutMapping("/{blockId}")
    fun updateBlock(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @PathVariable("blockId") blockId: Long,
        @RequestBody @Valid request: UpdateBlockRequest,
    ): ResponseEntity<ApiResponse<ApiResponse.EmptyBody>> {
        customerService.auth(uid = uid)

        blockServiceManager.updateBlock(
            blockId = blockId,
            request = request,
        )
        return ResponseEntity.ok(ApiResponse.success())
    }

    @DeleteMapping("/{blockId}")
    fun deleteBlock(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @PathVariable("blockId") blockId: Long,
    ): ResponseEntity<ApiResponse<ApiResponse.EmptyBody>> {
        customerService.auth(uid = uid)

        blockServiceManager.deleteBlock(blockId = blockId)
        return ResponseEntity.ok(ApiResponse.success())
    }
}
