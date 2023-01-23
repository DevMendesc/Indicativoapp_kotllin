package br.com.indicativo.repository

import br.com.indicativo.model.Ativos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AtivosRepository: JpaRepository<Ativos, Long> {

    fun findByNomeContainingIgnoreCase(nome: String): List<Ativos>

    fun findByNome(nome: String): Ativos

    fun deleteByNome(nome: String)

}