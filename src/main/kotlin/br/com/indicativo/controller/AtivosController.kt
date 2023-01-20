package br.com.indicativo.controller

import br.com.indicativo.model.Ativos
import br.com.indicativo.service.AtivosService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/ativos")
class AtivosController (
    val ativosService: AtivosService
) {

    fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.principal.toString()
    }

    @GetMapping
    fun getAllAtivos(): List<Ativos> {

        val username = getUsername()
        return ativosService.getAllAtivosByCnpj(username)
    }



    @GetMapping("{id}")
    fun getAtivosById(@PathVariable id: Long): ResponseEntity<Ativos>{
        return ResponseEntity(ativosService.getAtivosById(id),HttpStatus.OK )
    }

    @GetMapping("nomes/{nome}")
    fun getAtivosByNome(@PathVariable nome: String): ResponseEntity<List<Ativos>>{
        return ResponseEntity(ativosService.getAtivosByNome(nome), HttpStatus.OK)
    }

    @PostMapping
    fun saveAtivos(@RequestBody ativos: Ativos): ResponseEntity<Ativos> {
        val username: String = getUsername()
        return ResponseEntity<Ativos>(ativosService.saveAtivos(username, ativos), HttpStatus.CREATED)
    }

    @PutMapping("{nome}")
    fun updateAtivos(@PathVariable nome: String, @RequestBody ativos: Ativos): ResponseEntity<Ativos>{
        return ResponseEntity.ok(ativosService.updateAtivosByNome(ativos, nome))
    }

    @DeleteMapping("{nome}")
    fun deleteAtivos(@PathVariable nome: String): ResponseEntity<Unit> {
        ativosService.deleteAtivos(nome)
        return ResponseEntity.ok(ativosService.deleteAtivos(nome))
    }

}