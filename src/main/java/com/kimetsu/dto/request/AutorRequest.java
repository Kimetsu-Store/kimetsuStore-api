package com.kimetsu.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AutorRequest {

    @NotEmpty(message = "Campo Nome inválido")
    @Pattern(regexp = "[^\\d]+", message = "Campo Nome com caracteress inválido")
    @Size(max = 50, message = "Campo nome Ultrapassou o limite de caracteres")
    private String nome;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotNull(message = "Campo email inválido")
    @Email(message = "Campo Email inválido")
    @Size(max = 250, message = "Campo Email Ultrapassou o limite de caracteres")
    @Schema(description = "Número máx 250 caracteres", example = "teste@yahoo.com.br", required = true)
    private String email;

    @NotNull(message = "Campo Descrição inválido")
    @Size(min=4, max = 2500, message = "Campo descricao Ultrapassou o limite de caracteres")
    private String descricao;

}