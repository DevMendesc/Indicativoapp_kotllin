package br.com.indicativo.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Size

@Entity
class UserPF {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @NotNull
    @Column(unique = true)
    @Size(min = 11, max =11)
    val cpf: String = ""

    @NotNull
    var nome: String = ""

    @NotNull
    var email: String = ""

    @NotNull
    @Size(min = 11, max =11)
    var telefone: String = ""

    @NotNull
    var senha: String = ""


}