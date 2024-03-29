package com.portifolio.loginup.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException : RuntimeException{
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}