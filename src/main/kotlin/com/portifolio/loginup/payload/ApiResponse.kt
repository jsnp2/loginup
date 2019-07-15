package com.portifolio.loginup.payload

class ApiResponse {
    private var sucess: Boolean? = null
    private lateinit var message: String

    constructor(sucess: Boolean, message: String)
}