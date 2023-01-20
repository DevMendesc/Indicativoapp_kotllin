package br.com.indicativo.repository

import br.com.indicativo.model.UserPJ
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserPJRepository: JpaRepository<UserPJ, Long> {

    fun findByCnpj(cnpj: String): UserPJ

}