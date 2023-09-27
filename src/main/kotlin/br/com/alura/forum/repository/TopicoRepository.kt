package br.com.alura.forum.repository

import br.com.alura.forum.dto.TopicosPorCategoriaDto
import br.com.alura.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long> {

    // aqui, só pela estrutura do nome da função o Spring JPA
    // já sabe como fazer o SELECT. No caso a função precisa
    // começar por findBy, e aí vem o nome da tabela e o campo
    // de referência para a busca.
    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>

    // aqui um JPQL, formato de query de JPA. Não é obrigatorio, existe tb o modo
    // nativo (via parâmetro) para rodar SQL comum
    @Query("SELECT new br.com.alura.forum.dto.TopicosPorCategoriaDto(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun relatorio(): List<TopicosPorCategoriaDto>
}