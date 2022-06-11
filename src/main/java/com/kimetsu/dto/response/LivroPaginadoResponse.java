package com.kimetsu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class LivroPaginadoResponse {

    private int totalPaginas;
    private List<LivroResponse> livrosLista;
}
