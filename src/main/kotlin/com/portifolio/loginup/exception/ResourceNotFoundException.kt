package com.portifolio.loginup.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
private class ResourceNotFoundException : RuntimeException {
    private lateinit var resourceName : String
    private lateinit var fieldName: String
    private lateinit var fieldValue : Any

    constructor(resourceName: String, fieldName: String, fieldValue: Any) : super(String.format("%$resourceName not found with %$fieldName : '%$fieldValue'"))

}