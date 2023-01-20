package br.com.indicativo.service.impl

import br.com.indicativo.model.Indicadores
import br.com.indicativo.repository.IndicadoresRepository
import br.com.indicativo.service.IndicadoresService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class IndicadoresServiceImpl (
    val indicadoresRepository : IndicadoresRepository
): IndicadoresService {

    override fun getAllIndicadores(): List<Indicadores> {
        return indicadoresRepository.findAll()
    }

    override fun getIndicadoresByNome(nome: String): Indicadores {
        return indicadoresRepository.findByNome(nome)
    }

    override fun getAllIndicadoresByNome(nome: String): List<Indicadores> {
        return indicadoresRepository.findByNomeContainingIgnoreCase(nome = nome)
    }

    override fun saveIndicadores(indicadores: Indicadores): Indicadores {
        return indicadoresRepository.save(indicadores)
    }

    override fun deleteIndicadores(nome: String) {
        indicadoresRepository.deleteByNome(nome = nome)
    }
}