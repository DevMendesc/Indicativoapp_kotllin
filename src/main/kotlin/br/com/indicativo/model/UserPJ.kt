package br.com.indicativo.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "userPJ")
data class UserPJ(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @NotNull
    @Column(unique = true)
    @Size(min = 14, max =14)
    val cnpj: String,

    @NotNull
    var nome: String,

    @NotNull
    var email: String,

    @NotNull
    var telefone: String,

    @NotNull
    var senha: String,

    @OneToMany(mappedBy = "userPJ", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    @JsonIgnoreProperties("userPJ")
    internal val ativosList: MutableList<Ativos> = mutableListOf()

)