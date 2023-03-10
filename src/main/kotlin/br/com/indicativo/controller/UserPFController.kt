package br.com.indicativo.controller

import br.com.indicativo.model.UserPF
import br.com.indicativo.service.UserPfService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pf")
class UserPFController(
    private val userPfService: UserPfService
) {

    fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.principal.toString()
    }

    @GetMapping
    fun getUserPJByCpf(): ResponseEntity<UserPF> = ResponseEntity.ok(userPfService.getUserPFByCnpj(getUsername()))

    @PutMapping()
    fun updateUserPf(@RequestBody userPF: UserPF): ResponseEntity<UserPF> = ResponseEntity.ok(userPfService.updateUserPF(userPF))

    @DeleteMapping()
    fun deleteUserPf(): ResponseEntity<String> {
        userPfService.deleteUserPF(getUsername())
        return ResponseEntity.ok("Usuario deletado com sucesso")
    }
}