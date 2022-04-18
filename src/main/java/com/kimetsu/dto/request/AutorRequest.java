package com.kimetsu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AutorRequest {

    @NotEmpty(message = "Campo Nome inválido")
    @Size(max = 25, message = "Campo nome Ultrapassou o limite de caracteres")
    private String nome;

    @NotNull(message = "Campo email inválido")
    @Size(max = 35, message = "Campo email Ultrapassou o limite de caracteres")
    private String email;

    @NotNull(message = "Campo data da Criação inválido")
    @Size(max=20, message = "Campo data da Criação Ultrapassou o limite de caracteres")
    private LocalDateTime dataCriacao;

    @NotNull(message = "Campo Descrição inválido")
    @Size(min=4, max = 200, message = "Campo descricao Ultrapassou o limite de caracteres")
    private String descricao;


}