package com.kimetsu.mapper;

import com.kimetsu.domain.Categoria;
import com.kimetsu.dto.request.CategoriaRequest;
import com.kimetsu.dto.response.CategoriaResponse;

public abstract class CategoriaMapper {

    public static Categoria toCategoria(CategoriaRequest categoriaRequestBody) {
        return Categoria.builder()
                .nome(categoriaRequestBody.getNome())
                .descricao(categoriaRequestBody.getDescricao())
                .build();
    }

    public static CategoriaResponse toCategoriaResponse(Categoria categoria) {
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
    }
}
