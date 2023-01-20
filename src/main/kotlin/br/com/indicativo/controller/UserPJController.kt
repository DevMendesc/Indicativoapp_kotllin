package br.com.indicativo.controller

import br.com.indicativo.model.UserPJ
import br.com.indicativo.service.UserPJService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pj")
class UserPJController(
    private val userPJService: UserPJService
) {

    fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.principal.toString()
    }

    @GetMapping
    fun getUserPJByCnpj(): ResponseEntity<UserPJ> {
        val username = getUsername()
        return ResponseEntity.ok(userPJService.getUserPJByCnpj(username))
    }

    @PutMapping()
    fun updateUserPJ(@RequestBody userPJ: UserPJ): ResponseEntity<UserPJ> =
        ResponseEntity.ok(userPJService.updateUserPJ(userPJ))

    @DeleteMapping()
    fun deleteUserPJ():ResponseEntity<String> {
        val username = getUsername()
        userPJService.deleteUserPJ(username)
        return ResponseEntity.ok("Usuario deletado com sucesso")
    }

}