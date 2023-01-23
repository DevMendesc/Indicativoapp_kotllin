package br.com.indicativo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull


@Entity
@Table(name = "Ativos")
class Ativos(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @NotNull
    @Column(unique = true)
    var nome: String,

    @NotNull
    var lucroPorAcao: Double,

    @NotNull
    var dividaTotal: Double,

    @NotNull
    var caixaEEquivalentes: Double,

    @NotNull
    var receitaLiquida: Double,
    @NotNull
    var lucroLiquido: Double,

    @NotNull
    var lucroOperacionalLiquido: Double,

    @NotNull
    var acoesTotais: Double,

    @NotNull
    var cotacaoAcao: Double,

    @NotNull
    var precoAcao: Double,

    @NotNull
    var valorPatrimonialPorAcao: Double,

    @NotNull
    var patrimonioLiquido: Double,

    @NotNull
    var impostos: Double,

    @NotNull
    var juros: Double,

    @NotNull
    var depreciacao: Double,

    @NotNull
    var amortizacao: Double,

    @NotNull
    var dividendo: Double,

    @ManyToOne
    @JsonIgnore
    var userPJ: UserPJ,

    @OneToOne(
        orphanRemoval = true,
        cascade = [CascadeType.ALL]
    )
    @JsonIgnore
    var indicadores: Indicadores,
)