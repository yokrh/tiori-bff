package com.example.tiori.bff.controller

import com.example.tiori.bff.controller.request.CreateUserRequest
import com.example.tiori.bff.controller.response.ApiResponse
import com.example.tiori.bff.controller.response.CreateUserResponseBody
import com.example.tiori.bff.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/v1/customer")
class CustomerController(
    private val customerService: CustomerService,
) {
    @PostMapping
    fun createCustomer(
        @AuthenticationPrincipal user: OidcUser,
        @RequestBody @Valid request: CreateUserRequest,
    ): ResponseEntity<ApiResponse<CreateUserResponseBody>> {
        val response = customerService.saveOrGet(
            request = request,
            gid = user.name,
            gname = user.fullName,
            gmail = user.email,
        )
        return ResponseEntity.ok(ApiResponse.success(response))
    }
}
