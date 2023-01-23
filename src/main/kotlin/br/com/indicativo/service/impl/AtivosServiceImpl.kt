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
        return ativosRepository.findById(id).orElseThrow { RuntimeException() }
    }

    override fun getAtivosByNome(nome: String): List<Ativos> {
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

        ativos.userPJ = pj
        ativos.indicadores = indicadores

        indicadoresRepository.save(indicadores)
        return ativosRepository.save(ativos)
    }

    override fun updateAtivosByNome(ativos: Ativos, nome: String): Ativos {

        val ativo: Ativos = ativosRepository.findByNome(nome)
        val indicadores: Indicadores = indicadoresRepository.findByNome(nome)
        val atualizacaoAtivo: Ativos = ativo.apply {
            ativo.nome = ativo.nome
            ativo.lucroPorAcao = ativo.lucroPorAcao
            ativo.dividaTotal = ativo.dividaTotal
            ativo.caixaEEquivalentes = ativo.caixaEEquivalentes
            ativo.receitaLiquida = ativo.receitaLiquida
            ativo.lucroLiquido = ativo.lucroLiquido
            ativo.lucroOperacionalLiquido = ativo.lucroOperacionalLiquido
            ativo.acoesTotais = ativo.acoesTotais
            ativo.cotacaoAcao = ativo.cotacaoAcao
            ativo.precoAcao = ativo.precoAcao
            ativo.valorPatrimonialPorAcao = ativo.valorPatrimonialPorAcao
            ativo.patrimonioLiquido = ativo.patrimonioLiquido
            ativo.impostos = ativo.impostos
            ativo.juros = ativo.juros
            ativo.depreciacao = ativo.depreciacao
            ativo.amortizacao = ativo.amortizacao
            ativo.dividendo = ativo.dividendo
        }
        val atualizacaoIndicador: Indicadores = indicadores.apply {
            indicadores.nome = ativo.nome
            indicadores.pl = indicadores.plResult(
                precoAcao = ativo.precoAcao,
                lucroPorAcao = ativo.lucroPorAcao
            )
            indicadores.roe = indicadores.roeResult(
                lucroLiquido = ativo.lucroLiquido,
                patrimonioLiquido = ativo.patrimonioLiquido
            )

            indicadores.pvpa = indicadores.pvpaResult(
                precoAcao = ativo.precoAcao,
                valorPatrimonialPorAcao = ativo.valorPatrimonialPorAcao
            )

            indicadores.ev = indicadores.evResult(
                cotacaoAcao = ativo.cotacaoAcao,
                acoesTotais = ativo.acoesTotais,
                dividaTotal = ativo.dividaTotal,
                caixaEEquivalentes = ativo.caixaEEquivalentes
            )

            indicadores.ebitda = indicadores.ebitdaResult(
                lucroOperacionalLiquido = ativo.lucroOperacionalLiquido,
                juros = ativo.juros,
                impostos = ativo.impostos,
                depreciacao = ativo.depreciacao,
                amortizacao = ativo.amortizacao
            )

            indicadores.evebitda = indicadores.evEbitdaResult(
                cotacaoAcao = ativo.cotacaoAcao,
                acoesTotais = ativo.acoesTotais,
                dividaTotal = ativo.dividaTotal,
                caixaEEquivalentes = ativo.caixaEEquivalentes,
                lucroOperacionalLiquido = ativo.lucroOperacionalLiquido,
                juros = ativo.juros,
                impostos = ativo.impostos,
                depreciacao = ativo.depreciacao,
                amortizacao = ativo.amortizacao
            )

            indicadores.dividendYield = indicadores.dividendYieldResult(
                dividendo = ativo.dividendo,
                precoAcao = ativo.precoAcao
            )

            indicadores.lpa = indicadores.lpaResult(
                lucroLiquido = ativo.lucroLiquido,
                acoesTotais = ativo.acoesTotais
            )
        }
        indicadoresRepository.save(atualizacaoIndicador)
        ativosRepository.save(atualizacaoAtivo)

        return ativo
    }

    override fun deleteAtivos(nome: String) {
        ativosRepository.deleteByNome(nome)
    }
}