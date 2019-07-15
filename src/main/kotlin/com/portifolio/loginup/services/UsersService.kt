package com.portifolio.loginup.services

import com.portifolio.loginup.entity.User
import com.portifolio.loginup.entity.UserPrincipal
import com.portifolio.loginup.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UsersService(private val userRepository: UserRepository) : UserDetailsService{


    override fun loadUserByUsername(usernameOrEmail: String?): UserDetails {

        return UserPrincipal.create(userRepository.findByUsernameOrEmail(usernameOrEmail!!, usernameOrEmail) as User)
    }

    fun loadUserById(userId: Long): UserDetails {

        val possibleUser = userRepository.findByIdOrNull(userId)?: throw UsernameNotFoundException("Não foi possível encontrar o usuário com id:")

        return UserPrincipal.create(possibleUser as User)

    }


}
