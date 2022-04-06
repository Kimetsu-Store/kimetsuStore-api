package com.kimetsu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class CategoriaResponse {

    private Long id;
    private String descricao;
    private String nome;

}
