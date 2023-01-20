package br.com.indicativo.service.impl

import br.com.indicativo.model.Ativos
import br.com.indicativo.model.Indicadores
import br.com.indicativo.model.UserPJ
import br.com.indicativo.repository.AtivosRepository
import br.com.indicativo.repository.IndicadoresRepository
import br.com.indicativo.repository.UserPJRepository
import br.com.indicativo.service.AtivosService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AtivosServiceImpl(
    val ativosRepository: AtivosRepository,
    val indicadoresRepository: IndicadoresRepository,
    val userPJRepository: UserPJRepository
) : AtivosService {

    override fun getAllAtivosByCnpj(cnpj: String): List<Ativos> {
        val pj: UserPJ = userPJRepository.findByCnpj(cnpj)
        return pj.ativosList
    }

    override fun getAtivosById(id: Long): Ativos {
        return ativosRepository.findById(id).orElseThrow{RuntimeException()}
    }

    override fun getAtivosByNome(nome: String): List<Ativos>{
        return ativosRepository.findByNomeContainingIgnoreCase(nome)
    }

    override fun saveAtivos(cnpj: String, ativos: Ativos): Ativos {

        val pj: UserPJ = userPJRepository.findByCnpj(cnpj)

        val indicadores = Indicadores()

        indicadores.nome = ativos.nome

        indicadores.pl = indicadores.plResult(
            precoAcao = ativos.precoAcao,
            lucroPorAcao = ativos.lucroPorAcao
        )

        indicadores.roe = indicadores.roeResult(
            lucroLiquido = ativos.lucroLiquido,
            patrimonioLiquido = ativos.patrimonioLiquido
        )

        indicadores.pvpa = indicadores.pvpaResult(
            precoAcao = ativos.precoAcao,
            valorPatrimonialPorAcao = ativos.valorPatrimonialPorAcao
        )

        indicadores.ev = indicadores.evResult(
            cotacaoAcao = ativos.cotacaoAcao,
            acoesTotais = ativos.acoesTotais,
            dividaTotal = ativos.dividaTotal,
            caixaEEquivalentes = ativos.caixaEEquivalentes
        )

        indicadores.ebitda = indicadores.ebitdaResult(
            lucroOperacionalLiquido = ativos.lucroOperacionalLiquido,
            juros = ativos.juros,
            impostos = ativos.impostos,
            depreciacao = ativos.depreciacao,
            amortizacao = ativos.amortizacao
        )

        indicadores.evebitda = indicadores.evEbitdaResult(
            cotacaoAcao = ativos.cotacaoAcao,
            acoesTotais = ativos.acoesTotais,
            dividaTotal = ativos.dividaTotal,
            caixaEEquivalentes = ativos.caixaEEquivalentes,
            lucroOperacionalLiquido = ativos.lucroOperacionalLiquido,
            juros = ativos.juros,
            impostos = ativos.impostos,
            depreciacao = ativos.depreciacao,
            amortizacao = ativos.amortizacao
        )

        indicadores.dividendYield = indicadores.dividendYieldResult(
            dividendo = ativos.dividendo,
            precoAcao = ativos.precoAcao
        )

        indicadores.lpa = indicadores.lpaResult(
            lucroLiquido = ativos.lucroLiquido,
            acoesTotais = ativos.acoesTotais
        )

        ativos.userpj = pj

        indicadoresRepository.save(indicadores)
        return ativosRepository.save(ativos)
    }

    override fun updateAtivosByNome(ativos: Ativos, nome: String): Ativos{

        val existingAtivos: Ativos = ativosRepository.findByNome(nome)
        val indicadores: Indicadores = indicadoresRepository.findByNome(nome)

        existingAtivos.nome = ativos.nome
        existingAtivos.lucroPorAcao = ativos.lucroPorAcao
        existingAtivos.dividaTotal = ativos.dividaTotal
        existingAtivos.caixaEEquivalentes = ativos.caixaEEquivalentes
        existingAtivos.receitaLiquida = ativos.receitaLiquida
        existingAtivos.lucroLiquido = ativos.lucroLiquido
        existingAtivos.lucroOperacionalLiquido = ativos.lucroOperacionalLiquido
        existingAtivos.acoesTotais = ativos.acoesTotais
        existingAtivos.cotacaoAcao = ativos.cotacaoAcao
        existingAtivos.precoAcao = ativos.precoAcao
        existingAtivos.valorPatrimonialPorAcao = ativos.valorPatrimonialPorAcao
        existingAtivos.patrimonioLiquido = ativos.patrimonioLiquido
        existingAtivos.impostos = ativos.impostos
        existingAtivos.juros = ativos.juros
        existingAtivos.depreciacao = ativos.depreciacao
        existingAtivos.amortizacao = ativos.amortizacao
        existingAtivos.dividendo = ativos.dividendo

        indicadores.nome = ativos.nome

        indicadores.pl = indicadores.plResult(
            precoAcao = ativos.precoAcao,
            lucroPorAcao = ativos.lucroPorAcao
        )

        indicadores.roe = indicadores.roeResult(
            lucroLiquido = ativos.lucroLiquido,
            patrimonioLiquido = ativos.patrimonioLiquido
        )

        indicadores.pvpa = indicadores.pvpaResult(
            precoAcao = ativos.precoAcao,
            valorPatrimonialPorAcao = ativos.valorPatrimonialPorAcao
        )

        indicadores.ev = indicadores.evResult(
            cotacaoAcao = ativos.cotacaoAcao,
            acoesTotais = ativos.acoesTotais,
            dividaTotal = ativos.dividaTotal,
            caixaEEquivalentes = ativos.caixaEEquivalentes
        )

        indicadores.ebitda = indicadores.ebitdaResult(
            lucroOperacionalLiquido = ativos.lucroOperacionalLiquido,
            juros = ativos.juros,
            impostos = ativos.impostos,
            depreciacao = ativos.depreciacao,
            amortizacao = ativos.amortizacao
        )

        indicadores.evebitda = indicadores.evEbitdaResult(
            cotacaoAcao = ativos.cotacaoAcao,
            acoesTotais = ativos.acoesTotais,
            dividaTotal = ativos.dividaTotal,
            caixaEEquivalentes = ativos.caixaEEquivalentes,
            lucroOperacionalLiquido = ativos.lucroOperacionalLiquido,
            juros = ativos.juros,
            impostos = ativos.impostos,
            depreciacao = ativos.depreciacao,
            amortizacao = ativos.amortizacao
        )

        indicadores.dividendYield = indicadores.dividendYieldResult(
            dividendo = ativos.dividendo,
            precoAcao = ativos.precoAcao
        )

        indicadores.lpa = indicadores.lpaResult(
            lucroLiquido = ativos.lucroLiquido,
            acoesTotais = ativos.acoesTotais
        )



        indicadoresRepository.save(indicadores)
        ativosRepository.save(ativos)

        return existingAtivos
    }

    override fun deleteAtivos(nome: String) {
        indicadoresRepository.deleteByNome(nome)
        ativosRepository.deleteByNome(nome)
    }
}