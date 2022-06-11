package com.kimetsu.service;

import com.kimetsu.domain.Categoria;
import com.kimetsu.dto.request.CategoriaRequest;
import com.kimetsu.dto.response.CategoriaResponse;
import com.kimetsu.exception.NotFoundException;
import com.kimetsu.infra.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.kimetsu.stub.CategoriaStub.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepositoryMock;

    private Categoria categoriaComId;
    private CategoriaResponse categoriaResponse;
    private CategoriaRequest categoriaRequest;

    @BeforeEach
    void inicializar() {
        categoriaResponse = gerarCategoriaResponse();
        categoriaRequest = gerarCategoriaRequest();
        categoriaComId = gerarCategoriaComId();

        when(categoriaRepositoryMock.save(any(Categoria.class)))
                .thenReturn(categoriaComId);

        when(categoriaRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(categoriaComId));

        when(categoriaRepositoryMock.findByNome(any(String.class)))
                .thenReturn(Optional.ofNullable(categoriaComId));
    }

    @Test
    @DisplayName("Teste Salvar da Categoria/Service Sucesso")
    void saveTesteSucesso() {
        when(categoriaRepositoryMock.findByNome(any(String.class)))
                .thenReturn(Optional.empty());
        CategoriaResponse categoria = categoriaService.salvar(categoriaRequest);
        assertThat(categoria).isNotNull().isEqualTo(categoriaResponse);
    }

    @Test
    @DisplayName("Teste Salvar da Categoria/Service Error")
    void saveTesteError() {
        assertThrows(NullPointerException.class,
                () -> categoriaService.salvar(null));
    }

    @Test
    @DisplayName("Teste Buscar a Categoria pelo id/Service Sucesso")
    void buscaPorIdTesteSucesso() {
        CategoriaResponse categoria = categoriaService.buscaPorId(1L);
        assertThat(categoria).isNotNull().isEqualTo(categoriaResponse);
    }

    @Test
    @DisplayName("Teste Buscar a Categoria pelo id/Service Error")
    void buscaPorIdTesteError() {
        Throwable erro = assertThrows(NotFoundException.class,
                () -> categoriaService.buscaPorId(2L));
        assertEquals("Categoria não encontrada!", erro.getLocalizedMessage());
    }

    @Test
    @DisplayName("Teste Buscar todas Categorias Service ")
    void buscaTodasCategoriasTeste() {
        List<CategoriaResponse> categoria = categoriaService.buscarTodos();
        assertThat(categoria).isNotNull();
    }

    @Test
    @DisplayName("Teste Buscar a Categoria pelo nome/Service sucesso")
    void buscaPorNomeTeste() {
        CategoriaResponse categoria = categoriaService.buscaPorNome("Backend");
        assertThat(categoria).isNotNull().isEqualTo(categoriaResponse);
    }

    @Test
    @DisplayName("Teste Buscar a Categoria pelo nome/Service Error")
    void buscaPorNomeTesteError() {
        Throwable erro = assertThrows(NotFoundException.class,
                () -> categoriaService.buscaPorNome(null));
        assertEquals("Categoria não encontrada!", erro.getLocalizedMessage());
    }

    @Test
    @DisplayName("Teste Atualizar a Categoria/Service sucesso")
    void atualizarTeste() {
        CategoriaResponse categoria = categoriaService.atualizar(categoriaRequest, 1L);
        assertThat(categoria).isNotNull().isEqualTo(categoriaResponse);
    }

    @Test
    @DisplayName("Teste Atualizar a Categoria/Service error")
    void atualizarTesteError() {
        when(categoriaRepositoryMock.findByNome(any()))
                .thenReturn(Optional.empty());

        categoriaRequest.setNome("error");

        Throwable erro = assertThrows(NotFoundException.class,
                () -> categoriaService.atualizar(categoriaRequest, 4L));
        assertEquals("Categoria não encontrada!", erro.getLocalizedMessage());
    }

    @Test
    @DisplayName("Teste Deletar a Categoria pelo nome/Service sucesso")
    void deletarTeste() {
        categoriaService.deletar("delete");
    }


    @Test
    @DisplayName("Teste Deletar a Categoria pelo nome/Service Error")
    void deletarTesteError() {
        Throwable erro = assertThrows(NotFoundException.class,
                () -> categoriaService.deletar(null));
        assertEquals("Categoria não encontrada!", erro.getLocalizedMessage());
    }

    @Test
    @DisplayName("Teste Deletar a Categoria pelo nome/Service Error")
    void salvarComNomeDupplicadoError() {
        Throwable erro = assertThrows(DuplicateKeyException.class,
                () -> categoriaService.salvar(gerarCategoriaRequest()));
        assertEquals("Categoria já foi cadastrada!", erro.getLocalizedMessage());
    }
}
