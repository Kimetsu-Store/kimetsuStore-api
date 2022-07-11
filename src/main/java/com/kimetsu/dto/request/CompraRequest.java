package com.kimetsu.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
@Data
public class CompraRequest {

    @NotNull(message = "Campo id do livro não pode ser nulo")
    private Long idLivro;

    @Min(1)
    private Integer quandidadeDeLivros;

    @NotNull(message = "Campo nome não pode ser nulo")
    @Size(min=4, max = 250, message = "Campo nome Ultrapassou o limite de caracteres")
    private String nomeCliente;

    @CPF()
    @NotNull(message = "Campo Descrição inválido")
    @Size(max = 14, message = "Campo descricao Ultrapassou o limite de caracteres")
    private String cpf;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "email Iválido")
    @NotNull(message = "Campo email inválido")
    @Email(message = "Campo Email inválido")
    @Size(max = 250, message = "Campo Email Ultrapassou o limite de caracteres")
    private String email;

    @NotNull(message = "Campo Descrição inválido")
    @Size(max = 250, message = "Campo rua Ultrapassou o limite de caracteres")
    private String rua;

    @Size(max = 250, message = "Campo bairro Ultrapassou o limite de caracteres")
    private String bairro;

    @Size(max = 250, message = "Campo cidade Ultrapassou o limite de caracteres")
    private String cidade;

    @Size(max = 250, message = "Campo pais pais o limite de caracteres")
    private String pais;

    @Size(max = 250, message = "Campo estado Ultrapassou o limite de caracteres")
    private String estado;

    @Size(max = 9, message = "Campo CEP Ultrapassou o limite de caracteres")
    @Pattern(regexp="^\\d{5}(-\\d{3})?$", message = "CEP inválido")
    private String cep;

    @NotNull(message = "Campo numeroRua não foi informado")
    private Integer numeroRua;

    @Size(max = 250, message = "Campo complemento Ultrapassou o limite de caracteres")
    private String complemento;
}
