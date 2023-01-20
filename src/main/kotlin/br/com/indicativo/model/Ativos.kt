package br.com.indicativo.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull


@Entity
@Table(name = "Ativos")
class Ativos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(unique = true, nullable = false)
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

    @ManyToOne(cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("ativosList")
    var userpj: UserPJ = UserPJ()


}