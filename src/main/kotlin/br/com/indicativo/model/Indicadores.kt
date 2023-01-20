package br.com.indicativo.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull


@Entity
@Table(name = "Indicadores")
class Indicadores{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(nullable = true, unique = true)
    var nome: String = ""

    @NotNull
    var pl: Double = 0.0

    @NotNull
    var roe: Double = 0.0

    @NotNull
    var pvpa:Double = 0.0

    @NotNull
    var ev: Double = 0.0

    @NotNull
    var ebitda: Double = 0.0

    @NotNull
    var evebitda: Double = 0.0

    @NotNull
    var dividendYield: Double = 0.0

    @NotNull
    var lpa: Double = 0.0


    fun plResult(
        precoAcao: Double,
        lucroPorAcao: Double
    ): Double {
        return (precoAcao / lucroPorAcao)
    }

    fun roeResult(
        lucroLiquido: Double,
        patrimonioLiquido: Double
    ): Double {
        return ((lucroLiquido / patrimonioLiquido) * 100)
    }

    fun pvpaResult(
        precoAcao: Double,
        valorPatrimonialPorAcao: Double
    ): Double {
        return (precoAcao / valorPatrimonialPorAcao)
    }

    fun evResult(
        cotacaoAcao: Double,
        acoesTotais: Double,
        dividaTotal: Double,
        caixaEEquivalentes: Double
    ): Double {
        return (((cotacaoAcao * acoesTotais) + dividaTotal) - caixaEEquivalentes)
    }

    fun ebitdaResult(
        lucroOperacionalLiquido: Double,
        juros: Double,
        impostos: Double,
        depreciacao: Double,
        amortizacao: Double
    ): Double {
        return ((((lucroOperacionalLiquido + juros) + impostos) + depreciacao) + amortizacao)
    }

    fun evEbitdaResult(
        cotacaoAcao: Double,
        acoesTotais: Double,
        dividaTotal: Double,
        caixaEEquivalentes: Double,
        lucroOperacionalLiquido: Double,
        juros: Double,
        impostos: Double,
        depreciacao: Double,
        amortizacao: Double
    ): Double {
        return ((((cotacaoAcao * acoesTotais) + dividaTotal) - caixaEEquivalentes) /
                (((((lucroOperacionalLiquido + juros) + impostos) + depreciacao) + amortizacao)))
    }

    fun dividendYieldResult(
        dividendo: Double,
        precoAcao: Double
    ): Double {
        return ((dividendo / precoAcao) * 100)
    }

    fun lpaResult(
        lucroLiquido: Double,
        acoesTotais: Double
    ): Double {
        return (lucroLiquido / acoesTotais)
    }

}