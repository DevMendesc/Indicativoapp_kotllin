package br.com.indicativo.controller

import br.com.indicativo.model.UserPF
import br.com.indicativo.model.UserPJ
import br.com.indicativo.service.UserPJService
import br.com.indicativo.service.UserPfService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("cadastrar")
class CadastroController (
    private val userPJService: UserPJService,
    private val userPfService: UserPfService
){

    //Cadastro pessoa juridica
    @PostMapping("cnpj")
    fun cadastrarUserPJ(@RequestBody userPJ: UserPJ): ResponseEntity<UserPJ> {
        return ResponseEntity.ok(userPJService.cadastrarUserPJ(userPJ))
    }

    //Cadastro pessoa fisica
    @PostMapping("cpf")
    fun cadastrarUserPF(@RequestBody userPF: UserPF): ResponseEntity<UserPF> {
        return ResponseEntity.ok(userPfService.cadastrarUserPF(userPF))
    }

}