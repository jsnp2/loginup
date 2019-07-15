package com.portifolio.loginup.auth.config

class JwtAuthenticationResponse {

    private lateinit var acessToken: String
    private var tokenType = "Bearer"

    constructor(acessToken: String)

}
