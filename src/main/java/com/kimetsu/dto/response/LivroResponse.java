package com.kimetsu.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LivroResponse {

    private Long id;
    private String titulo;
    private AutorResponse autor;
    private CategoriaResponse categoria;
    private String descricao;
    private String sumario;
    private BigDecimal preco;
    private int paginas;
    private String isbn;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDePublicacao;

    private String capa;
    private int estoque;
}
