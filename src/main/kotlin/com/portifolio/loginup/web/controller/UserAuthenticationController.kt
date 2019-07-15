package com.portifolio.loginup.web.controller

import com.portifolio.loginup.auth.config.JwtAuthenticationResponse
import com.portifolio.loginup.entity.Role
import com.portifolio.loginup.entity.Rolename
import com.portifolio.loginup.entity.User
import com.portifolio.loginup.exception.AppException
import com.portifolio.loginup.payload.ApiResponse
import com.portifolio.loginup.payload.LoginRequest
import com.portifolio.loginup.payload.SignUpRequest
import com.portifolio.loginup.repository.RoleRepository
import com.portifolio.loginup.repository.UserRepository
import com.portifolio.loginup.security.jwt.TokenManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class UserAuthenticationController {

    @Autowired
    private lateinit var authManager: AuthenticationManager

    @Autowired
    private lateinit var tokenManager: TokenManager

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {

        val authenticate = authManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.usernameOrEmail, loginRequest.password))
        SecurityContextHolder.getContext().authentication = authenticate

        return ResponseEntity.ok(JwtAuthenticationResponse(tokenManager.generateToken(authenticate)))
    }


    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<Any> {
        userRepository.let {
            if (it.existsByUsername(signUpRequest.username)) return ResponseEntity(ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST)
            if (it.existsByUsername(signUpRequest.username)) return ResponseEntity(ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST)
            if (it.existsByEmail(signUpRequest.email)) return ResponseEntity(ApiResponse(false, "Email Address already in use"), HttpStatus.BAD_REQUEST)
        }

        var user = User(signUpRequest.name, signUpRequest.username, signUpRequest.email, passwordEncoder.encode(signUpRequest.password))

        var roleName = roleRepository.findByNameRole(Rolename.ROLE_USER).orElseThrow()
                ?: AppException("User Role not set.")

//Validar esse trecho de codigo
        user.roles = Collections.singleton(roleName as Role)

        val result = userRepository.save(user)

        val location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.username).toUri()

        return ResponseEntity.created(location).body(ApiResponse(true, "User registered successfully"))
    }


}