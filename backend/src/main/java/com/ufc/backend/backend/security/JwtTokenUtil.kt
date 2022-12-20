package com.ufc.backend.backend.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil {

    private val secret = "TESTE"
    private val expiration = 6000000

    fun generateToken(username: String): String {
        return Jwts.builder().setSubject(username).setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray()).compact()
    }


    private fun getClaims(token: String) =
        Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body


    fun getEmail(token: String): String {
        val claims = getClaims(token)
        return claims.subject
    }

    fun isTokenValid(token: String): Boolean {
        val claims = getClaims(token)
        val expirationDate = claims.expiration
        val now = Date(System.currentTimeMillis())
        return now.before(expirationDate)
    }

}