package com.kimetsu.mapper;
import com.kimetsu.domain.Autor;
import com.kimetsu.dto.request.AutorRequest;
import com.kimetsu.dto.response.AutorResponse;

public abstract class AutorMapper {

    public static Autor toAutor(AutorRequest autorRequestBody) {
        return Autor.builder()
                .nome(autorRequestBody.getNome())
                .descricao(autorRequestBody.getDescricao())
                .email(autorRequestBody.getEmail())
                .dataCriacao(autorRequestBody).getDataCriacao()
                .build();
    }

    public static AutorResponse toAutorResponse(Autor autor) {
        return AutorResponse.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .descricao(autor.getDescricao())
                .email(autor.getEmail())
                .dataCriacao(autor.getDataCriacao)
                .build();
    }
}
