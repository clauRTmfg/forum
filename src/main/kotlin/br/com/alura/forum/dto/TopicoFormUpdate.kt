package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicoFormUpdate (
    @field:NotNull
    val id: Long,
    @field:Size(min = 5, max = 100, message = "deve conter de 5 a 100 caracteres")
    val titulo: String,
    @field:NotEmpty(message = "não pode ser em branco")
    val mensagem: String
)
