package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoFormUpdate
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.dto.TopicosPorCategoriaDto
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {
    fun listar(nomeCurso: String?,
               paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = nomeCurso?.let {
            repository.findByCursoNome(nomeCurso, paginacao)
        } ?: repository.findAll(paginacao)
        return topicos.map {
            topicoViewMapper.map(it)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow { NotFoundException() }
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: TopicoFormUpdate): TopicoView {
        val topico = repository.findById(form.id).orElseThrow { NotFoundException() }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun remover(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicosPorCategoriaDto> {
        return repository.relatorio()
    }
}


