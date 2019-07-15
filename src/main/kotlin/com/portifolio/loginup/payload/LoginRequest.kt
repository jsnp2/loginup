package com.portifolio.loginup.payload

data class LoginRequest(var usernameOrEmail: String,
                        var password: String) {

}