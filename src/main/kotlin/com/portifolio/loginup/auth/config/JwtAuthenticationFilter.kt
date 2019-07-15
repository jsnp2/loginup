package com.portifolio.loginup.auth.config

import com.portifolio.loginup.security.jwt.TokenManager
import com.portifolio.loginup.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var tokenManager: TokenManager

    @Autowired
    private lateinit var tokenProvider : JwtTokenProvider

    @Autowired
    private lateinit var usersService: UsersService

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val jwt = getTokenFromRequest(request)

        if(tokenManager.isValid(jwt)){ var userDetails = usersService.loadUserById(tokenManager.getUserIdFromToken(jwt))}

        filterChain.doFilter(request, response)

    }

    private fun getTokenFromRequest(request: HttpServletRequest): String {

        var bearerToken = request.getHeader("Authorization")

        if (org.springframework.util.StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) return bearerToken.substring(7, bearerToken.length)

        return ""
    }


}