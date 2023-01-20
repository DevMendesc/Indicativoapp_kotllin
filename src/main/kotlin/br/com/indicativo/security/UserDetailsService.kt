package br.com.indicativo.security

import br.com.indicativo.model.UserSecurity
import br.com.indicativo.repository.UserPFRepository
import br.com.indicativo.repository.UserPJRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsService(
    private val userPJRepository: UserPJRepository,
    private val userPFRepository: UserPFRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        if (username.length == 11) {
            val user = userPFRepository.findByCpf(username)
            return UserSecurity(
                user.id,
                user.cpf,
                user.senha,
                Collections.singleton(SimpleGrantedAuthority("user"))
            )
        }else if (username.length == 14) {
            val user = userPJRepository.findByCnpj(username)
            return UserSecurity(
                user.id,
                user.cnpj,
                user.senha,
                Collections.singleton(SimpleGrantedAuthority("user"))
            )
        }

        return null
    }
}