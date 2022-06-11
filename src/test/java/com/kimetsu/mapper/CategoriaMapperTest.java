package com.kimetsu.mapper;

import com.kimetsu.domain.Categoria;
import com.kimetsu.dto.request.CategoriaRequest;
import com.kimetsu.dto.response.CategoriaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.kimetsu.mapper.CategoriaMapper.*;
import static com.kimetsu.stub.CategoriaStub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoriaMapperTest {
    private Categoria categoria;
    private CategoriaRequest categoriaRequest;
    private CategoriaResponse categoriaResponse;
    private Categoria categoriaEsperada;

    @BeforeEach
    void inicializar() {
        categoria = gerarCategoriaComId();
        categoriaRequest = gerarCategoriaRequest();
        categoriaResponse = gerarCategoriaResponse();
        categoriaEsperada = Categoria.builder()
                .nome(categoriaRequest.getNome())
                .descricao(categoriaRequest.getDescricao())
                .build();
    }

    @Test
    @DisplayName("Teste de conversão CategoriaRequest para Categoria SUCESSO")
    void toCategoriaTeste() {
        Categoria categoriaResultado = toCategoria(categoriaRequest);
        assertEquals(categoriaEsperada, categoriaResultado);
    }

    @Test
    @DisplayName("Teste de conversão Categoria para CategoriaResponse SUCESSO")
    void toCategoriaResponseBodyTeste() {
        CategoriaResponse categoriaResultado = toCategoriaResponse(categoria);
        assertEquals(categoriaResponse, categoriaResultado);
    }
}
