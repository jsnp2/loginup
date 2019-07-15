package com.portifolio.loginup.repository

import com.portifolio.loginup.entity.Role
import com.portifolio.loginup.entity.Rolename
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, Long> {

    fun findByNameRole(rolename: Rolename): Optional<Role>
}