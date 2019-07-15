package com.portifolio.loginup.payload

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignUpRequest(@field:NotBlank
                         @Size(min = 4, max = 40)
                         var name : String,
                         @field:NotBlank
                         @Size(min = 3, max = 15)
                         var username: String,
                         @field:NotBlank
                         @Size(max = 40)
                         @Email
                         var email: String,
                         @field:NotBlank
                         @Size(min=6, max = 20)
                         var password: String)