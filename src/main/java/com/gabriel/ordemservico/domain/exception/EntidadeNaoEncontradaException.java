package com.gabriel.ordemservico.domain.exception;

public class EntidadeNaoEncontradaException extends  NegocioException{

    private static final long seriaVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
