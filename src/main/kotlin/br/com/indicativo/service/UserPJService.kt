package br.com.indicativo.service

import br.com.indicativo.model.UserPJ

interface UserPJService{

    fun getAllUserPJ(): List<UserPJ>
    fun getUserPJById(id: Long): UserPJ
    fun getUserPJByCnpj(cnpj: String): UserPJ
    fun saveUserPJ(userPJ: UserPJ): UserPJ
    fun updateUserPJ(userPJ: UserPJ): UserPJ
    fun deleteUserPJ(cnpj: String)
    fun cadastrarUserPJ(userPJ: UserPJ): UserPJ

}