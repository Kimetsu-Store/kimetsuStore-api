package com.kimetsu.domain;

public enum SatatusCompra {
    AGUARDANDO_ENVIO("AGUARDANDO"),
    ENVIADO("ENVIADO");

    private String descricao;
    SatatusCompra(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
