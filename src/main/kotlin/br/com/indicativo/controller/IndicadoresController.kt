package br.com.indicativo.controller

import br.com.indicativo.model.Indicadores
import br.com.indicativo.service.IndicadoresService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/indicadores")
class IndicadoresController (
    val indicadoresRepository: IndicadoresService
    ) {

    @GetMapping
    fun getAllIndicadores(): List<Indicadores> = indicadoresRepository.getAllIndicadores()

    @GetMapping("/nomes/{nome}")
    fun getAllIndicadoresByNome(@PathVariable nome: String): ResponseEntity<List<Indicadores>> = ResponseEntity.ok(indicadoresRepository.getAllIndicadoresByNome(nome))

    @GetMapping("{nome}")
    fun getIndicadoresByNome(@PathVariable nome: String): ResponseEntity<Indicadores> = ResponseEntity.ok(indicadoresRepository.getIndicadoresByNome(nome))
}