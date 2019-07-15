package com.portifolio.loginup.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors
import javax.persistence.*

@Entity
data class UserPrincipal(@Id @GeneratedValue(strategy = GenerationType.AUTO)
                         var id: Long,
                         var name: String,
                         private var username: String,
                         @Column(name = "email", unique = true)
                         var email: String?,
                         private var password: String,
                         @ManyToMany(fetch = FetchType.EAGER)
                         private var authorities: Collection<GrantedAuthority>

): UserDetails {

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return this.email!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return this.password

    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    companion object {
        fun create(user: User): UserPrincipal {

            val authorities = user.roles?.stream()!!.map { role -> SimpleGrantedAuthority(role.nameRole.name) }.collect(Collectors.toList())
            return UserPrincipal(user.id!!, user.name!!, user.username!!, user.email, user.password!!, authorities)
        }

    }

    override fun equals(other: Any?): Boolean {
        if(this === other) true
        if(other == null || javaClass != other.javaClass) false

        var that = other as UserPrincipal
        return Objects.equals(id, that.id)
    }


    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}
