package br.com.indicativo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull


@Entity
@Table(name = "Ativos")
class Ativos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @NotNull
    @Column(unique = true)
    var nome: String = ""

    @NotNull
    var lucroPorAcao: Double = 0.0

    @NotNull
    var dividaTotal: Double = 0.0

    @NotNull
    var caixaEEquivalentes: Double = 0.0

    @NotNull
    var receitaLiquida: Double = 0.0

    @NotNull
    var lucroLiquido: Double = 0.0

    @NotNull
    var lucroOperacionalLiquido: Double = 0.0

    @NotNull
    var acoesTotais: Double = 0.0

    @NotNull
    var cotacaoAcao: Double = 0.0

    @NotNull
    var precoAcao: Double = 0.0

    @NotNull
    var valorPatrimonialPorAcao: Double = 0.0

    @NotNull
    var patrimonioLiquido: Double = 0.0

    @NotNull
    var impostos: Double = 0.0

    @NotNull
    var juros: Double = 0.0

    @NotNull
    var depreciacao: Double = 0.0

    @NotNull
    var amortizacao: Double = 0.0

    @NotNull
    var dividendo: Double = 0.0


    @ManyToOne
    @JsonIgnore
    lateinit var userPJ: UserPJ

    @OneToOne(orphanRemoval = true)
    @JsonIgnore
    lateinit var indicadores: Indicadores


}