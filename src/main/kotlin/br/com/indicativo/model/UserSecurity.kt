package br.com.indicativo.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserSecurity(
    val id: Long,
    val usuario: String,
    private val uPassword: String,
    private val uAuthorities: MutableCollection<GrantedAuthority>
) : UserDetails {
    override fun getAuthorities() = uAuthorities
    override fun getPassword() = uPassword
    override fun getUsername() = usuario
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired()= true
    override fun isEnabled() = true
}