package com.example.tiori.bff.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@Validated
@RestController
@RequestMapping("/v1/auth")
class AuthController() {
    @Value("\${app.frontend.url}")
    private lateinit var frontendUrl: String

    @GetMapping
    fun auth(
        @AuthenticationPrincipal user: OidcUser,
    ): RedirectView {
        println(user.name)
        println(user.email)
        println(user.fullName)

        val rv = RedirectView()
        rv.url = frontendUrl
        return rv
    }
}
