package com.example.tiori.bff.controller

import com.example.tiori.bff.controller.request.*
import com.example.tiori.bff.controller.response.*
import com.example.tiori.bff.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/v1/customer")
class CustomerController(
    private val customerService: CustomerService,
) {
    @PostMapping
    fun createCustomer(
        @RequestBody @Valid request: CreateUserRequest,
    ): ResponseEntity<ApiResponse<CreateUserResponseBody>> {
        val response = customerService.save(request)
        return ResponseEntity.ok(ApiResponse.success(response))
    }
}
