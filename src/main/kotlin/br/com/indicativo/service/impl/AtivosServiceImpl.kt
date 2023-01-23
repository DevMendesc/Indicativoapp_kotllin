package br.com.indicativo.service.impl

import br.com.indicativo.model.Ativos
import br.com.indicativo.model.Indicadores
import br.com.indicativo.repository.AtivosRepository
import br.com.indicativo.repository.IndicadoresRepository
import br.com.indicativo.repository.UserPJRepository
import br.com.indicativo.service.AtivosService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AtivosServiceImpl(
    private val ativosRepository: AtivosRepository,
    private val indicadoresRepository: IndicadoresRepository,
    private val userPJRepository: UserPJRepository
) : AtivosService {

    override fun getAllAtivosByCnpj(cnpj: String): List<Ativos> = userPJRepository.findByCnpj(cnpj).ativosList

    override fun getAtivosById(id: Long): Ativos = ativosRepository.findById(id).orElseThrow{RuntimeException()}

    override fun getAtivosByNome(nome: String): List<Ativos> = ativosRepository.findByNomeContainingIgnoreCase(nome)

    override fun saveAtivos(cnpj: String, ativos: Ativos): Ativos {
        val indicador = Indicadores()
        ativos.userPJ = userPJRepository.findByCnpj(cnpj)
        ativos.indicadores = indicador

        indicador.apply {
            nome = ativos.nome

            pl = indicador.plResult(
                precoAcao = ativos.precoAcao,
                lucroPorAcao = ativos.lucroPorAcao
            )

            roe = indicador.roeResult(
                lucroLiquido = ativos.lucroLiquido,
                patrimonioLiquido = ativos.patrimonioLiquido
            )

            pvpa = indicador.pvpaResult(
                precoAcao = ativos.precoAcao,
                valorPatrimonialPorAcao = ativos.valorPatrimonialPorAcao
            )

            ev = indicador.evResult(
                cotacaoAcao = ativos.cotacaoAcao,
                acoesTotais = ativos.acoesTotais,
                dividaTotal = ativos.dividaTotal,
                caixaEEquivalentes = ativos.caixaEEquivalentes
            )

            ebitda = indicador.ebitdaResult(
                lucroOperacionalLiquido = ativos.lucroOperacionalLiquido,
                juros = ativos.juros,
                impostos = ativos.impostos,
                depreciacao = ativos.depreciacao,
                amortizacao = ativos.amortizacao
            )

            evebitda = indicador.evEbitdaResult(
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

            dividendYield = indicador.dividendYieldResult(
                dividendo = ativos.dividendo,
                precoAcao = ativos.precoAcao
            )

            lpa = indicador.lpaResult(
                lucroLiquido = ativos.lucroLiquido,
                acoesTotais = ativos.acoesTotais
            )
        }
        indicadoresRepository.save(indicador)
        return ativosRepository.save(ativos)
    }

    override fun updateAtivosByNome(ativos: Ativos, name: String): Ativos{
        val existingAtivos: Ativos = ativosRepository.findByNome(name)
        val indicador: Indicadores = indicadoresRepository.findByNome(name)

        existingAtivos.apply {
            nome = ativos.nome
            lucroPorAcao = ativos.lucroPorAcao
            dividaTotal = ativos.dividaTotal
            caixaEEquivalentes = ativos.caixaEEquivalentes
            receitaLiquida = ativos.receitaLiquida
            lucroLiquido = ativos.lucroLiquido
            lucroOperacionalLiquido = ativos.lucroOperacionalLiquido
            acoesTotais = ativos.acoesTotais
            cotacaoAcao = ativos.cotacaoAcao
            precoAcao = ativos.precoAcao
            valorPatrimonialPorAcao = ativos.valorPatrimonialPorAcao
            patrimonioLiquido = ativos.patrimonioLiquido
            impostos = ativos.impostos
            juros = ativos.juros
            depreciacao = ativos.depreciacao
            amortizacao = ativos.amortizacao
            dividendo = ativos.dividendo
        }

        indicador.apply {
            nome = ativos.nome

            pl = indicador.plResult(
                precoAcao = ativos.precoAcao,
                lucroPorAcao = ativos.lucroPorAcao
            )

            roe = indicador.roeResult(
                lucroLiquido = ativos.lucroLiquido,
                patrimonioLiquido = ativos.patrimonioLiquido
            )

            pvpa = indicador.pvpaResult(
                precoAcao = ativos.precoAcao,
                valorPatrimonialPorAcao = ativos.valorPatrimonialPorAcao
            )

            ev = indicador.evResult(
                cotacaoAcao = ativos.cotacaoAcao,
                acoesTotais = ativos.acoesTotais,
                dividaTotal = ativos.dividaTotal,
                caixaEEquivalentes = ativos.caixaEEquivalentes
            )

            ebitda = indicador.ebitdaResult(
                lucroOperacionalLiquido = ativos.lucroOperacionalLiquido,
                juros = ativos.juros,
                impostos = ativos.impostos,
                depreciacao = ativos.depreciacao,
                amortizacao = ativos.amortizacao
            )

            evebitda = indicador.evEbitdaResult(
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

            dividendYield = indicador.dividendYieldResult(
                dividendo = ativos.dividendo,
                precoAcao = ativos.precoAcao
            )

            lpa = indicador.lpaResult(
                lucroLiquido = ativos.lucroLiquido,
                acoesTotais = ativos.acoesTotais
            )
        }
        indicadoresRepository.save(indicador)
        ativosRepository.save(existingAtivos)
        return existingAtivos
    }

    override fun deleteAtivos(nome: String) = ativosRepository.deleteByNome(nome)
/*
    override fun verificaAtivos(nome: String): Boolean {
        val usuario = userPJRepository.findByCnpj(getUsername())
        usuario.ativosList.forEach { if (nome == it.nome) return true }
        return false
    }
    fun getUsername(): String {
        return SecurityContextHolder.getContext().authentication.principal.toString()
    }

 */


}