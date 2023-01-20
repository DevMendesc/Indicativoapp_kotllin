package br.com.indicativo.service.impl

import br.com.indicativo.model.UserPJ
import br.com.indicativo.repository.UserPJRepository
import br.com.indicativo.service.UserPJService
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserPJServiceImpl(
    val userPJRepository: UserPJRepository
): UserPJService {

    private fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.principal.toString()
    }

    override fun getAllUserPJ(): List<UserPJ> = userPJRepository.findAll()

    override fun getUserPJById(id: Long): UserPJ = userPJRepository.findById(id).orElseThrow{NotFoundException()}

    override fun getUserPJByCnpj(cnpj: String): UserPJ = userPJRepository.findByCnpj(cnpj)

    override fun saveUserPJ(userPJ: UserPJ): UserPJ = userPJRepository.save(userPJ)

    override fun updateUserPJ(userPJ: UserPJ): UserPJ {
        val username = getUsername()
        val usuario = userPJRepository.findByCnpj(username)
        val encoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
        val senhaEncoder = encoder.encode(userPJ.senha)

        usuario.nome = userPJ.nome
        usuario.email = userPJ.email
        usuario.telefone = userPJ.telefone
        usuario.senha = senhaEncoder
        return userPJRepository.save(usuario)
    }

    override fun deleteUserPJ(cnpj: String) {
        val user = userPJRepository.findByCnpj(cnpj)
        userPJRepository.delete(user)
    }

    override fun cadastrarUserPJ(userPJ: UserPJ): UserPJ {
        val encoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
        val senhaEncoder = encoder.encode(userPJ.senha)
        userPJ.senha = senhaEncoder
        return userPJRepository.save(userPJ)
    }
}