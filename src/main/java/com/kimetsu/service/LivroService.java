package com.kimetsu.service;

import com.kimetsu.domain.Autor;
import com.kimetsu.domain.Categoria;
import com.kimetsu.domain.Livro;
import com.kimetsu.dto.request.LivroRequest;
import com.kimetsu.dto.response.LivroPaginadoResponse;
import com.kimetsu.dto.response.LivroResponse;
import com.kimetsu.exception.NotFoundException;
import com.kimetsu.infra.AutorRepository;
import com.kimetsu.infra.CategoriaRepository;
import com.kimetsu.infra.LivroRepository;
import com.kimetsu.mapper.LivroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kimetsu.mapper.LivroMapper.*;

@Service
public class LivroService {

    private final LivroRepository livroRepositorio;
    private final CategoriaRepository categoriaRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LivroService( LivroRepository repositorio, CategoriaRepository categoriaRepository, AutorRepository autorRepository) {
        this.livroRepositorio = repositorio;
        this.categoriaRepository = categoriaRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public LivroResponse salvar(LivroRequest livroRequestBody) {
        Categoria categoria = buscarCategoria(livroRequestBody.getCategoria_id());
        Autor autor = buscarAutor(livroRequestBody.getAutor_id());
        Livro livro = toLivroComAutorECategoria(livroRequestBody, categoria, autor);
        return toLivroComAutorECategoriaResponse(livroRepositorio.save(livro));
    }

    @Transactional
    public LivroResponse atualizar(LivroRequest livroRequestBody, Long id) {
        Livro livro = buscaLivroPorId(id);
        livro.setCategoria(buscarCategoria(livroRequestBody.getCategoria_id()));
        livro.setAutor(buscarAutor(livroRequestBody.getAutor_id()));
        livro.setDescricao(livroRequestBody.getDescricao());
        livro.setTitulo(livroRequestBody.getTitulo());
        livro.setIsbn(livroRequestBody.getIsbn());
        livro.setSumario(livroRequestBody.getSumario());
        livro.setPreco(livroRequestBody.getPreco());
        livro.setPaginas(livroRequestBody.getPaginas());
        livro.setCapa(livroRequestBody.getCapa());
        livro.setEstoque(livroRequestBody.getEstoque());
        return toLivroComAutorECategoriaResponse(livro);
    }

    private Livro buscaLivroPorId(Long id) {
        return livroRepositorio.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado!"));
    }

    public LivroResponse buscaPorId(Long id) {
        return toLivroComAutorECategoriaResponse(livroRepositorio.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado!")));
    }

    public LivroPaginadoResponse buscaPaginada(Pageable pageable, String palavra) {
        if (palavra == null) {
            return buscaTodosPaginada(pageable);
        } else {
            return buscaTodosPorTituloOuNomeDoAutorPaginado(pageable, palavra);
        }
    }

    private LivroPaginadoResponse buscaTodosPaginada(Pageable pageable) {
        return toLivroPaginadoResponse(livroRepositorio.findAll(pageable)
                .map(LivroMapper::toLivroComAutorECategoriaResponse));
    }

    private LivroPaginadoResponse buscaTodosPorTituloOuNomeDoAutorPaginado(Pageable pageable, String palavra) {
        return toLivroPaginadoResponse(livroRepositorio.findAllByTituloContainsOrAutorNomeContainsAllIgnoreCase(pageable, palavra, palavra)
                .map(LivroMapper::toLivroComAutorECategoriaResponse));
    }

    @Transactional
    public void deletar(Long id) {
        buscaPorId(id).getId();
        livroRepositorio.deleteById(id);
    }

    private Autor buscarAutor(Long id) {
        return autorRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Autor(a) Inválido!"));
    }

    private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Categoria Inválida!"));
    }

    public LivroPaginadoResponse buscaPaginadaPorCategoria(Pageable pageable, String categoria) {
        return toLivroPaginadoResponse(livroRepositorio.findAllByCategoriaNome(pageable, categoria)
                .map(LivroMapper::toLivroComAutorECategoriaResponse));
    }

}
