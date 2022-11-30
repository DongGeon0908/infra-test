package com.goofy.infratest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebMvcSupportConfig : WebMvcConfigurationSupport() {
    @Bean
    fun corsFilter(): CorsFilter? {
        val config = CorsConfiguration()
        config.addAllowedOrigin(CorsConfiguration.ALL)
        config.addAllowedHeader(CorsConfiguration.ALL)
        config.addAllowedMethod(CorsConfiguration.ALL)

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)

        return CorsFilter(source)
    }
}
