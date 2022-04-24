package com.kimetsu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaRequest {

    @NotEmpty(message = "Campo Nome não pode ser nulo ou vazio")
    @Size(min =4 , max = 25, message = "Campo nome Ultrapassou o limite de caracteres ou menor que 4 caracteres")
    private String nome;

    @NotNull(message = "Campo Descrição inválido")
    @Size(max = 500, message = "Campo descricao Ultrapassou o limite de caracteres")
    private String descricao;

}
