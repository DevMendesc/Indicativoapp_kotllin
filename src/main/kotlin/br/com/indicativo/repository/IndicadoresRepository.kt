package br.com.indicativo.repository

import br.com.indicativo.model.Indicadores
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IndicadoresRepository: JpaRepository<Indicadores, Long> {

    fun findByNomeContainingIgnoreCase(nome: String): List<Indicadores>

    fun findByNome(nome: String): Indicadores

    fun deleteByNome(nome: String)

}