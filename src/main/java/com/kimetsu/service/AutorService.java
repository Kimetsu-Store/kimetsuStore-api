package com.kimetsu.service;

import com.kimetsu.domain.Autor;
import com.kimetsu.dto.request.AutorRequest;
import com.kimetsu.dto.response.AutorResponse;
import com.kimetsu.exception.NotFoundException;
import com.kimetsu.infra.AutorRepository;
import com.kimetsu.mapper.AutorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepositorio;

    @Autowired
    public AutorService(AutorRepository repositorio) {
        autorRepositorio = repositorio;
    }

    @Transactional
    public AutorResponse salvar(AutorRequest autorRequestBody) {
        verificaSeNomeJaFoiCadastrado(autorRequestBody.getNome());
        Autor autor = toAutor(autorRequestBody);
        return toAutorResponse(AutorRepositorio.save(autor));
    }

    public AutorResponse buscaPorId(Long id) {
        return toAutorResponse(AutorRepositorio.findById(id)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado!")));
    }

    public List<AutorResponse> buscarTodos() {
        return AutorRepositorio.findAll().stream().map(AutorMapper::toAutorResponse).toList();
    }

    public AutorResponse buscaPorNome(String nome) {
        return toAutorResponse(AutorRepositorio.findByNome(nome)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado!")));
    }
    //cadastrar todas os atributos de autor
    @Transactional
    public AutorResponse atualizar(AutorRequest autorRequestBody, Long id) {
        AutorResponse autor = buscaPorId(id);
        return toAutorResponse(AutorRepositorio.save(Autor.builder()
                .id(autor.getId())
                .nome(autorRequestBody.getNome())
                .descricao(autorRequestBody.getDescricao())
                .email(autorRequestBody.getEmail())
                .dataCriacao(autorRequestBody.getDataCriacao())
                .build()));
    }

    @Transactional
    public void deletar(String nome) {
        Long id = buscaPorNome(nome).getId();
        autorRepositorio.deleteById(id);
    }

    private void verificaSeNomeJaFoiCadastrado(String nome) {
        if (autorRepositorio.findByNome(nome).isPresent()) {
            throw new DuplicateKeyException("Nome desse autor já foi cadastrado!");
        }
    }
}
