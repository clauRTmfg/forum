package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoFormUpdate
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: MutableList<Topico> = mutableListOf(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {
    fun listar(): List<TopicoView> {
        return topicos.map {
            topicoViewMapper.map(it)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico =  topicos.filter {
            it.id == id
        }.firstOrNull()?.let {
            return topicoViewMapper.map(it)
        } ?: throw (NotFoundException())
    }

    fun cadastrar(form: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos.add(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: TopicoFormUpdate): TopicoView {
        val topico =  topicos.filter {
            it.id == form.id
        }.firstOrNull()?.let {
            topicos.remove(it)
            val topicoAtualizado = Topico(
                id = form.id,
                titulo = form.titulo,
                mensagem = form.mensagem,
                autor = it.autor,
                curso = it.curso,
                respostas = it.respostas,
                status = it.status,
                dataCriacao = it.dataCriacao
            )
            topicos.add(topicoAtualizado)
            return topicoViewMapper.map(topicoAtualizado)
        } ?: throw (NotFoundException())
    }

    fun remover(id: Long) {
        val topico = topicos.filter {
            it.id == id
        }.firstOrNull()?.let {
            topicos.remove(it)
        } ?: throw (NotFoundException())
    }
}