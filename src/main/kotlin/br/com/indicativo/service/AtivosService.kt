package br.com.indicativo.service

import br.com.indicativo.model.Ativos

interface AtivosService {

    fun getAllAtivosByCnpj(cnpj: String): List<Ativos>

    fun getAtivosById(id: Long): Ativos

    fun getAtivosByNome(nome: String): List<Ativos>

    fun saveAtivos(cnpj: String, ativos: Ativos): Ativos

    fun updateAtivosByNome(ativos: Ativos, nome: String): Ativos

    fun deleteAtivos(nome: String)
}