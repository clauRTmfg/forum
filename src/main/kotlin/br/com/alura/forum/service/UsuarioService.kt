package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private var usuarios: List<Usuario>
) {
    init {
        val usuario = Usuario(
            id = 1,
            nome = "ClauRT",
            email = "crt@gmail.com"
        )
        usuarios = listOf(usuario)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.filter {
            it.id == id
        }.first()
    }

}
