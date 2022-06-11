package com.kimetsu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class AutorResponse {

        private Long id;
        private String nome;
        private String email;
        private String descricao;
        private LocalDateTime dataCriacao;

}
