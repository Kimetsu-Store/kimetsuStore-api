package com.kimetsu.stub;

import com.kimetsu.domain.Categoria;
import com.kimetsu.dto.request.CategoriaRequest;
import com.kimetsu.dto.response.CategoriaResponse;

public class CategoriaStub {

    public static Categoria gerarCategoria() {
        return Categoria.builder()
                .nome("Backend")
                .descricao("Livros de backend")
                .build();
    }

    public static CategoriaResponse gerarCategoriaResponse() {
        return CategoriaResponse.builder()
                .nome("Backend")
                .descricao("Livros de backend")
                .build();
    }

    public static CategoriaRequest gerarCategoriaRequest() {
        return new CategoriaRequest("Backend", "Livros de backend");
    }

}
