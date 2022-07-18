package com.kimetsu.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
@Data
public class CompraRequest {

    @NotNull(message = "Campo id do livro não pode ser nulo")
    private Long idLivro;

    @Min(1)
    @Schema(description = "Número minímo de um", example = "1")
    private Integer quandidadeDeLivros;

    @NotNull(message = "Campo nome não pode ser nulo")
    @Size(min=4, max = 250, message = "Campo nome Ultrapassou o limite de caracteres")
    private String nomeCliente;

    @CPF()
    @NotNull(message = "Campo CPF inválido")
    @Size(max = 14, message = "Campo descricao Ultrapassou o limite de caracteres")
    @Schema(description = "Número máx 14 caracteres", example = "68901859009")
    private String cpf;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "email Iválido")
    @NotNull(message = "Campo email inválido")
    @Email(message = "Campo Email inválido")
    @Size(max = 250, message = "Campo Email Ultrapassou o limite de caracteres")
    @Schema(description = "Número máx 250 caracteres", example = "teste@yahoo.com.br")
    private String email;

    @NotNull(message = "Campo Descrição inválido")
    @Size(max = 250, message = "Campo rua Ultrapassou o limite de caracteres")
    @Schema(description = "Número máx 250 caracteres", example = "João de Oliveira")
    private String rua;

    @Size(max = 250, message = "Campo bairro Ultrapassou o limite de caracteres")
    @Schema(description = "Número máx 250 caracteres", example = "Auxiliadora")
    private String bairro;

    @Size(max = 250, message = "Campo cidade Ultrapassou o limite de caracteres")
    @Schema(description = "Número máx 250 caracteres", example = "Porto Alegre")
    private String cidade;

    @Size(max = 250, message = "Campo pais pais o limite de caracteres")
    @Schema(description = "Número máx 250 caracteres", example = "Brasil")
    private String pais;

    @Size(max = 250, message = "Campo estado Ultrapassou o limite de caracteres")
    @Schema(description = "Número máx 250 caracteres", example = "RS")
    private String estado;

    @Size(max = 9, message = "Campo CEP Ultrapassou o limite de caracteres")
    @Pattern(regexp="^\\d{5}(-\\d{3})?$", message = "CEP inválido")
    @Schema(description = "CEP", example = "90050-230")
    private String cep;

    @NotNull(message = "Campo numeroRua não foi informado")
    private Integer numeroRua;

    @Size(max = 250, message = "Campo complemento Ultrapassou o limite de caracteres")
    @Schema(description = "Número máx 250 caracteres", example = "ap 130 bloco C")
    private String complemento;
}
