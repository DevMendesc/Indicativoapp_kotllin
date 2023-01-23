package br.com.indicativo.service

import br.com.indicativo.model.UserPF

interface UserPfService {

    fun getAllUserPF(): List<UserPF>
    fun getUserPFById(id: Long): UserPF
    fun getUserPFByCnpj(cpf: String): UserPF
    fun saveUserPF(userPF: UserPF): UserPF
    fun updateUserPF(userPF: UserPF): UserPF
    fun deleteUserPF(cpf: String)
    fun cadastrarUserPF(userPF: UserPF): UserPF

}