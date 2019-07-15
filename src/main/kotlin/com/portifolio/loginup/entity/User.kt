package com.portifolio.loginup.entity

import com.portifolio.loginup.entity.audit.DateAudit
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


data class User( @NotBlank
                 var name: String,
                 @NotBlank
                 var username: String,
                 @Size(max = 40)
                 @Email
                 var email: String,
                 @NotBlank
                 var password: String
) : DateAudit() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long? = null

    @NotBlank
    @ManyToMany(fetch = FetchType.LAZY)//fazer mapeamente de entidades
    lateinit var roles: Set<Role>


}



