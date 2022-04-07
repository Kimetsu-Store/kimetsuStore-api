package com.kimetsu.stub;

import com.kimetsu.domain.Categoria;
import com.kimetsu.dto.request.CategoriaRequest;
import com.kimetsu.dto.response.CategoriaResponse;

public class CategoriaStub {

    public static CategoriaResponse gerarCategoriaResponse() {
        return CategoriaResponse.builder()
                .id(1L)
                .nome("Backend")
                .descricao("Livros de backend")
                .build();
    }

    public static CategoriaResponse gerarCategoriaResponse(String texto) {
        return CategoriaResponse.builder()
                .id(1L)
                .nome("Backend")
                .descricao(texto)
                .build();
    }

    public static CategoriaRequest gerarCategoriaRequest() {
        return new CategoriaRequest("Backend", "Livros de backend");
    }

    public static Categoria gerarCategoriaComId() {
        return Categoria.builder()
                .nome("Backend")
                .descricao("Livros de backend")
                .id(1L)
                .build();
    }
}
