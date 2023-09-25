package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.NotNull

data class TopicoForm(
    @field:Size(min = 5, max = 100, message = "deve conter de 5 a 100 caracteres")
    val titulo: String,
    @field:NotEmpty(message = "não pode ser em branco")
    val mensagem: String,
    @field:NotNull
    val idCurso: Long,
    @field:NotNull
    val idAutor: Long
)
