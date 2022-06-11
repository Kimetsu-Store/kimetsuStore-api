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

import static com.kimetsu.mapper.AutorMapper.toAutor;
import static com.kimetsu.mapper.AutorMapper.toAutorResponse;

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
        return toAutorResponse(autorRepositorio.save(autor));
    }

    public AutorResponse buscaPorId(Long id) {
        return toAutorResponse(autorRepositorio.findById(id)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado!")));
    }

    public List<AutorResponse> buscarTodos() {
        return autorRepositorio.findAll().stream().map(AutorMapper::toAutorResponse).toList();
    }

    public AutorResponse buscaPorNome(String nome) {
        return toAutorResponse(autorRepositorio.findByNome(nome)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado!")));
    }

    @Transactional
    public AutorResponse atualizar(AutorRequest autorRequestBody, Long id) {
        AutorResponse autor = buscaPorId(id);
        return toAutorResponse(autorRepositorio.save(Autor.builder()
                .id(autor.getId())
                .nome(autorRequestBody.getNome())
                .descricao(autorRequestBody.getDescricao())
                .email(autorRequestBody.getEmail())
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
