package br.com.indicativo.controller

import br.com.indicativo.model.Ativos
import br.com.indicativo.service.AtivosService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/ativos")
@Tag(name = "ativos", description = "Controller para requisições de Ativos")
@SecurityRequirement(name = "Bearer Authentication")
class AtivosController (
    val ativosService: AtivosService
) {

    private final fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.principal.toString()
    }
    val username = getUsername()

    @GetMapping
    @Operation(
        summary = ("Apresentação de ativos"),
        description = ("Apresentação de todos os ativos cadastrados pela empresa responsavel"),
        tags = ["ativos"]
    )
    fun getAllAtivos(): List<Ativos> = ativosService.getAllAtivosByCnpj(username)

    @GetMapping("{id}")
    fun getAtivosById(@PathVariable id: Long): ResponseEntity<Ativos> = ResponseEntity.ok(ativosService.getAtivosById(id))

    @GetMapping("nomes/{nome}")
    fun getAtivosByNome(@PathVariable nome: String): ResponseEntity<List<Ativos>> = ResponseEntity.ok(ativosService.getAtivosByNome(nome))

    @PostMapping
    fun saveAtivos(@RequestBody ativos: Ativos): ResponseEntity<Ativos> = ResponseEntity.ok(ativosService.saveAtivos(username, ativos))

    @PutMapping("{nome}")
    fun updateAtivos(@PathVariable nome: String, @RequestBody ativos: Ativos): ResponseEntity<Ativos> = ResponseEntity.ok(ativosService.updateAtivosByNome(ativos, nome))

    @DeleteMapping("{nome}")
    fun deleteAtivos(@PathVariable nome: String) = ativosService.deleteAtivos(nome)

}