package com.portifolio.loginup.dto


class AuthenticationTokenOutputDto {

    private lateinit var tokenType : String
    private lateinit var token : String

    constructor(tokenType: String, token: String)
}
