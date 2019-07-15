package com.portifolio.loginup.entity.audit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.Instant
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(
//        value = {"createdAt" ,"updatedAt"},
        allowGetters = true

)
open class DateAudit : Serializable {
    private lateinit var createdAt : Instant
    private lateinit var updatedAt : Instant


}
