package br.com.indicativo.repository

import br.com.indicativo.model.UserPF
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserPFRepository: JpaRepository<UserPF, Long> {

    fun findByCpf(cpf: String): UserPF
}