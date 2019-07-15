package com.portifolio.loginup.dto

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class LoginInputDto {

 private lateinit var  email : String
 private lateinit var  password : String


    fun  build() : UsernamePasswordAuthenticationToken{
        return UsernamePasswordAuthenticationToken(this.email,this.password)
    }

}
