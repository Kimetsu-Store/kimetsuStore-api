package com.kimetsu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimetsu.dto.request.CategoriaRequest;
import com.kimetsu.dto.response.CategoriaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.kimetsu.SqlProvider.inserirCategoria;
import static com.kimetsu.SqlProvider.resetaDB;
import static com.kimetsu.stub.CategoriaStub.gerarCategoriaRequest;
import static com.kimetsu.stub.CategoriaStub.gerarCategoriaResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private CategoriaRequest categoriaRequest;
    private CategoriaResponse categoriaResponse;
    private ObjectMapper mapper = new ObjectMapper();
    private String retornoComoJson;
    private String retornoComoJsonList;
    private String envioComoJSON;

    @BeforeEach
    public void inicializar() throws JsonProcessingException {
        categoriaRequest = gerarCategoriaRequest();
        categoriaResponse = gerarCategoriaResponse();
        retornoComoJson = mapper.writeValueAsString(categoriaResponse);
        retornoComoJsonList = mapper.writeValueAsString(List.of(categoriaResponse));
        envioComoJSON = mapper.writeValueAsString(categoriaRequest);
    }

    @DisplayName("Teste POST/Sucesso da categoria")
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetaDB)
    public void testSalva() throws Exception {
        mockMvc.perform(post("/livraria/categorias")
                        .content(envioComoJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(retornoComoJson))
                .andExpect(status().isCreated());
    }

    @DisplayName("Teste POST/Error da categoria com nome vazio")
    @Test
    public void testSalvaComCampoinvalido() throws Exception {
        categoriaRequest.setNome("");
        envioComoJSON = mapper.writeValueAsString(categoriaRequest);

        mockMvc.perform(post("/livraria/categorias")
                        .content(envioComoJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste POST/Error da categoria com descrição invalida")
    @Test
    public void testSalvaComCampoInvalidoDescricao() throws Exception {
        categoriaRequest.setDescricao(null);
        envioComoJSON = mapper.writeValueAsString(categoriaRequest);

        mockMvc.perform(post("/livraria/categorias")
                        .content(envioComoJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste POST/Error categoria Nome vazio")
    @Test
    public void testSalvaErroNomeVazio() throws Exception {
        categoriaRequest.setNome("");
        envioComoJSON = mapper.writeValueAsString(categoriaRequest);

        mockMvc.perform(post("/livraria/categorias")
                        .content(envioComoJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste POST/Erros categoria Descrição vazia")
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetaDB)
    public void testSalvaDescricaoVazia() throws Exception {
        categoriaRequest.setDescricao("");
        envioComoJSON = mapper.writeValueAsString(categoriaRequest);

        mockMvc.perform(post("/livraria/categorias")
                        .content(envioComoJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste POST/Error categoria Descrição inválida")
    @Test
    public void testSalvaErroDescricaoInvalida() throws Exception {
        categoriaRequest.setDescricao(null);
        envioComoJSON = mapper.writeValueAsString(categoriaRequest);

        mockMvc.perform(post("/livraria/categorias")
                        .content(envioComoJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste GET/SUCESSO buscar por todas categorias")
    @Test
    public void testBuscarTodos() throws Exception {
        mockMvc.perform(get("/livraria/categorias"))
                .andExpect(status().isOk());
    }

    @DisplayName("Teste GET/SUCESSO buscar uma categorias")
    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = inserirCategoria),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = resetaDB)
    })
    public void testgerPorId() throws Exception {
        mockMvc.perform(get("/livraria/categorias/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(retornoComoJson))
                .andExpect(status().isOk());
    }

    @DisplayName("Teste GET/Error")
    @Test
    public void testgerPorIdInvalido() throws Exception {
        mockMvc.perform(get("/livraria/categorias/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }
}