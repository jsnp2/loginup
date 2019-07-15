package com.portifolio.loginup.entity

import org.hibernate.annotations.NaturalId
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*


@Entity
data class Role(@Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           var id: Long,
           @Enumerated(EnumType.STRING)
           @NaturalId
           @Column(length = 60)
           var nameRole : Rolename
)
