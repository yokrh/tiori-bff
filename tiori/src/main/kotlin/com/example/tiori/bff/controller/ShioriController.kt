package com.example.tiori.bff.controller

import com.example.tiori.bff.controller.request.CommonHeader
import com.example.tiori.bff.controller.request.CreateShioriRequest
import com.example.tiori.bff.controller.request.LoadShioriRequest
import com.example.tiori.bff.controller.request.UpdateShioriRequest
import com.example.tiori.bff.controller.response.ApiResponse
import com.example.tiori.bff.controller.response.CreateShioriResponseBody
import com.example.tiori.bff.controller.response.GetShioriListResponseBody
import com.example.tiori.bff.controller.response.GetShioriResponseBody
import com.example.tiori.bff.service.CustomerService
import com.example.tiori.bff.service.ShioriServiceManager
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/v1/shiori")
class ShioriController(
    private val shioriServiceManager: ShioriServiceManager,
    private val customerService: CustomerService,
) {
    @GetMapping("/list")
    fun getShioriList(
        @AuthenticationPrincipal user: OidcUser,
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
    ): ResponseEntity<ApiResponse<GetShioriListResponseBody>> {
        val customerId = customerService.auth(uid = uid)

        val response = shioriServiceManager.getShioriList(customerId = customerId)
        return ResponseEntity.ok(ApiResponse.success(response))
    }

    @GetMapping("/{shioriId}")
    fun getShiori(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @PathVariable("shioriId") shioriId: Long,
    ): ResponseEntity<ApiResponse<GetShioriResponseBody>> {
        val customerId = customerService.auth(uid = uid)

        val response = shioriServiceManager.getShiori(
            customerId = customerId,
            shioriId = shioriId,
        )
        return ResponseEntity.ok(ApiResponse.success(response))
    }

    @PostMapping
    fun createShiori(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @RequestBody @Valid request: CreateShioriRequest,
    ): ResponseEntity<ApiResponse<CreateShioriResponseBody>> {
        val customerId = customerService.auth(uid = uid)

        val response = shioriServiceManager.createShiori(
            customerId = customerId,
            request = request,
        )
        return ResponseEntity.ok(ApiResponse.success(response))
    }

    @PutMapping("/{shioriId}")
    fun updateShiori(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @PathVariable("shioriId") shioriId: Long,
        @RequestBody @Valid request: UpdateShioriRequest,
    ): ResponseEntity<ApiResponse<ApiResponse.EmptyBody>> {
        customerService.auth(uid = uid)

        shioriServiceManager.updateShiori(
            shioriId = shioriId,
            request = request
        )
        return ResponseEntity.ok(ApiResponse.success())
    }

    @DeleteMapping("/{shioriId}")
    fun deleteShiori(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @PathVariable("shioriId") shioriId: Long,
    ): ResponseEntity<ApiResponse<ApiResponse.EmptyBody>> {
        customerService.auth(uid = uid)

        shioriServiceManager.deleteShiori(shioriId = shioriId)
        return ResponseEntity.ok(ApiResponse.success())
    }

    @PostMapping("/load")
    fun loadShiori(
        @RequestHeader(CommonHeader.TIORI_API_HEADER) @Valid @NotNull uid: String,
        @RequestBody @Valid request: LoadShioriRequest,
    ): ResponseEntity<ApiResponse<ApiResponse.EmptyBody>> {
        val customerId = customerService.auth(uid = uid)

        shioriServiceManager.loadShiori(
            customerId = customerId,
            request = request,
        )
        return ResponseEntity.ok(ApiResponse.success())
    }
}
