package br.com.alura.forum.exception

class NotFoundException(message: String = "Tópico não encontrado"): RuntimeException(message)
