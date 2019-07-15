package com.portifolio.loginup.auth.config

import com.portifolio.loginup.entity.UserPrincipal
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(    @Value("\${app.jwtSecret}")
                           var jwtSecret : String,

                           @Value("\${app.jwtExpirationInMs}")
                           var jwtExpirationInMs: Long
) {
    val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    fun generateToken(authentication: Authentication): String{

        val userPrincipal = authentication.principal as UserPrincipal

        val expiryDate = Date(Date().time + jwtExpirationInMs)

        return Jwts.builder()
                .setSubject(userPrincipal.id.toString())
                .setIssuedAt(Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    fun getUserIdFromJWT(token : String): Long{
        val claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body

        return claims.subject.toLong()
    }

    fun validateToken (authToken: String) : Boolean{

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true

        }catch (e : SignatureException ){
            logger.error("Invalid JWT signature")
        }catch (e : MalformedJwtException){
            logger.error("Invalid JWT token")
        }catch (e : ExpiredJwtException){
            logger.error("Expired JWT token")
        }catch (e : UnsupportedJwtException ){
            logger.error("Unsupported JWT token")
        }catch (e : IllegalArgumentException ){
            logger.error("JWT claims string is empty.")
        }

        return false

    }
}

