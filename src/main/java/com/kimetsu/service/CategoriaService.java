package com.kimetsu.service;

import com.kimetsu.domain.Categoria;
import com.kimetsu.dto.request.CategoriaRequest;
import com.kimetsu.dto.response.CategoriaResponse;
import com.kimetsu.exception.NotFoundException;
import com.kimetsu.infra.CategoriaRepository;
import com.kimetsu.mapper.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static com.kimetsu.mapper.CategoriaMapper.toCategoria;
import static com.kimetsu.mapper.CategoriaMapper.toCategoriaResponse;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepositorio;

    @Autowired
    public CategoriaService(CategoriaRepository repositorio) {
        categoriaRepositorio = repositorio;
    }

    @Transactional
    public CategoriaResponse salvar(CategoriaRequest categoriaRequestBody) {
        verificaSeNomeJaFoiCadastrado(categoriaRequestBody.getNome());
        Categoria categoria = toCategoria(categoriaRequestBody);
        return toCategoriaResponse(categoriaRepositorio.save(categoria));
    }

    public CategoriaResponse buscaPorId(Long id) {
        return toCategoriaResponse(categoriaRepositorio.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada!")));
    }

    public List<CategoriaResponse> buscarTodos() {
        return categoriaRepositorio.findAll().stream().map(CategoriaMapper::toCategoriaResponse).toList();
    }

    public CategoriaResponse buscaPorNome(String nome) {
        return toCategoriaResponse(categoriaRepositorio.findByNome(nome)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada!")));
    }

    @Transactional
    public CategoriaResponse atualizar(CategoriaRequest categoriaRequestBody, Long id) {
        CategoriaResponse categoria = buscaPorId(id);
        return toCategoriaResponse(categoriaRepositorio.save(Categoria.builder()
                .id(categoria.getId())
                .nome(categoriaRequestBody.getNome())
                .descricao(categoriaRequestBody.getDescricao())
                .build()));
    }

    @Transactional
    public void deletar(String nome) {
        Long id = buscaPorNome(nome).getId();
        categoriaRepositorio.deleteById(id);
    }

    private void verificaSeNomeJaFoiCadastrado(String nome) {
        if (categoriaRepositorio.findByNome(nome).isPresent()) {
            throw new DuplicateKeyException("Categoria já foi cadastrada!");
        }
    }
}
