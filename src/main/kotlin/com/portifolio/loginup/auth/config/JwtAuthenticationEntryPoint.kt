package com.portifolio.loginup.auth.config

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    private var logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint::class.java)

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        logger.error("Responding with unauthorized error. Message - {}", authException?.message)
        response?.sendError(SC_UNAUTHORIZED, authException?.message)
    }
}