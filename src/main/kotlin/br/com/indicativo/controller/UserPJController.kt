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

    private final fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.principal.toString()
    }
    val username = getUsername()

    @GetMapping
    fun getUserPJByCnpj(): ResponseEntity<UserPJ> = ResponseEntity.ok(userPJService.getUserPJByCnpj(username))

    @PutMapping()
    fun updateUserPJ(@RequestBody userPJ: UserPJ): ResponseEntity<UserPJ> = ResponseEntity.ok(userPJService.updateUserPJ(userPJ))

    @DeleteMapping()
    fun deleteUserPJ():ResponseEntity<String> {
        userPJService.deleteUserPJ(username)
        return ResponseEntity.ok("Usuario deletado com sucesso")
    }

}