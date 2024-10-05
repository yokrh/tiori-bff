package com.example.tiori.bff.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@Configuration
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests { authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers("/v1/customer").permitAll()
                    .requestMatchers("/oauth2/authorization/google").permitAll()
                    .requestMatchers("/h2-console").permitAll()
                    .anyRequest().authenticated()
            }
            .headers { h -> h.frameOptions { f -> f.sameOrigin() } } // for h2 console
            .csrf{ csrf -> csrf.disable() }
            .cors{ cors -> cors.configurationSource(corsConfigurationSource()) }
            .oauth2Login(Customizer.withDefaults())
            .build()
    }

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = mutableListOf("http://localhost:5173", "https://accounts.google.com")
        configuration.allowedMethods = mutableListOf("*")
        configuration.allowedHeaders = mutableListOf("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}