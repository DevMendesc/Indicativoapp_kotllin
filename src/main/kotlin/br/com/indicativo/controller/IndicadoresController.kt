package br.com.indicativo.controller

import br.com.indicativo.model.Indicadores
import br.com.indicativo.repository.IndicadoresRepository
import br.com.indicativo.service.IndicadoresService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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
    fun getAllIndicadores(): List<Indicadores>{
        return indicadoresRepository.getAllIndicadores()
    }

    @GetMapping("/nomes/{nome}")
    fun getAllIndicadoresByNome(@PathVariable nome: String): ResponseEntity<List<Indicadores>>{
        return ResponseEntity(indicadoresRepository.getAllIndicadoresByNome(nome), HttpStatus.OK)
    }

    @GetMapping("{nome}")
    fun getIndicadoresByNome(@PathVariable nome: String): ResponseEntity<Indicadores>{
        return ResponseEntity(indicadoresRepository.getIndicadoresByNome(nome), HttpStatus.OK)
    }
}