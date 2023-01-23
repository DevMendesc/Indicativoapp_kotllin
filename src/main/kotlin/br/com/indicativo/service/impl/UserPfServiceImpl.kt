package br.com.indicativo.service.impl

import br.com.indicativo.model.UserPF
import br.com.indicativo.repository.UserPFRepository
import br.com.indicativo.service.UserPfService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserPfServiceImpl (
    private val userPFRepository: UserPFRepository
): UserPfService {
    fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.principal.toString()
    }

    override fun getAllUserPF(): List<UserPF> = userPFRepository.findAll()

    override fun getUserPFById(id: Long): UserPF = userPFRepository.findById(id).orElseThrow{RuntimeException()}

    override fun getUserPFByCnpj(cpf: String): UserPF = userPFRepository.findByCpf(cpf)

    override fun saveUserPF(userPF: UserPF): UserPF = userPFRepository.save(userPF)

    override fun updateUserPF(userPF: UserPF): UserPF {
        val username = getUsername()
        val usuario = userPFRepository.findByCpf(username)
        val encoder = BCryptPasswordEncoder()
        val senhaEncoder = encoder.encode(userPF.senha)

        usuario.nome = userPF.nome
        usuario.email = userPF.email
        usuario.telefone = userPF.telefone
        usuario.senha = senhaEncoder
        return userPFRepository.save(usuario)
    }

    override fun deleteUserPF(cpf: String) {
        val user = userPFRepository.findByCpf(cpf)
        userPFRepository.delete(user)
    }

    override fun cadastrarUserPF(userPF: UserPF): UserPF {
        val encoder = BCryptPasswordEncoder()
        val senhaEncoder = encoder.encode(userPF.senha)
        userPF.senha = senhaEncoder
        return userPFRepository.save(userPF)
    }
}