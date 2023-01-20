package br.com.indicativo.service

import br.com.indicativo.model.Indicadores

interface IndicadoresService {

   fun getAllIndicadores(): List<Indicadores>

   fun getIndicadoresByNome(nome: String): Indicadores

   fun getAllIndicadoresByNome(nome: String): List<Indicadores>

   fun saveIndicadores(indicadores: Indicadores): Indicadores

   fun deleteIndicadores(nome: String)

}