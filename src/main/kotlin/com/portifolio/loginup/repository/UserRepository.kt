package com.portifolio.loginup.repository

import com.portifolio.loginup.entity.User
import com.portifolio.loginup.entity.UserPrincipal
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email : String )
    fun findByUsernameOrEmail(username: String, email: String): Optional<User>
    fun findByIdIn(usersIds: List<Long>) : List<User>
    fun findByUsername(username: String): Optional<User>
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String) : Boolean
}
