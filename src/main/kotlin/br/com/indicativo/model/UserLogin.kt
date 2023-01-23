package br.com.indicativo.model


import java.beans.ConstructorProperties

data class UserLogin
@ConstructorProperties("usuario", "senha")
constructor(val usuario: String, val senha: String)