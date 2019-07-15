package com.portifolio.loginup.security.jwt

import com.portifolio.loginup.entity.UserPrincipal
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenManager {

    @Value("\${aluraTest.secrect}")
    private lateinit var secret: String

    @Value("\${expiration}")
    private var expirationInMillis: Long? = null

    fun generateToken(authentication: Authentication): String {

        var user = authentication.principal as UserPrincipal

        var now = Date()
        val expiration = Date(now.time + this.expirationInMillis!!)

        return Jwts.builder()
                .setIssuer("Portifolio Login")
                //rever ess trecho de codigo por que ele passa o id, e nao consigo passar o id
                .setSubject(user.username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.ES256, this.secret)
                .compact()
    }

    fun isValid(jwt: String): Boolean {

        return try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt)
            true
        } catch (e: JwtException) {
            false
        }

    }


    fun getUserIdFromToken(jwt: String): Long {

        val claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt).body

        return claims.subject.toLong()

    }


}