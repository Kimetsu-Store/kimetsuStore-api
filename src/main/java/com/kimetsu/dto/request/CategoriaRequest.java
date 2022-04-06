package com.kimetsu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaRequest {

    @NotEmpty(message = "Campo Nome inválido")
    @Size(max = 25, message = "Campo nome Ultrapassou o limite de caracteres")
    private String nome;

    @NotNull(message = "Campo Descrição inválido")
    @Size(max = 500, message = "Campo descricao Ultrapassou o limite de caracteres")
    private String descricao;
}
