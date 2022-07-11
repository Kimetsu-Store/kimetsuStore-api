package com.kimetsu.dto.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class EnderecoResponse {

    private String rua;

    private String bairro;

    private String cidade;

    private String cep;

    private String estado;

    private Integer numero;

    private String complemento;
}
