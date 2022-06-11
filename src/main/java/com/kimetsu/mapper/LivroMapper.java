package com.kimetsu.mapper;


import com.kimetsu.domain.Autor;
import com.kimetsu.domain.Categoria;
import com.kimetsu.domain.Livro;
import com.kimetsu.dto.request.LivroRequest;
import com.kimetsu.dto.response.AutorResponse;
import com.kimetsu.dto.response.CategoriaResponse;
import com.kimetsu.dto.response.LivroPaginadoResponse;
import com.kimetsu.dto.response.LivroResponse;
import org.springframework.data.domain.Page;

public abstract class LivroMapper {

    public static Livro toLivro(LivroRequest livroRequestBody) {
        return Livro.builder()
                .titulo(livroRequestBody.getTitulo())
                .descricao(livroRequestBody.getDescricao())
                .sumario(livroRequestBody.getSumario())
                .preco(livroRequestBody.getPreco())
                .paginas(livroRequestBody.getPaginas())
                .isbn(livroRequestBody.getIsbn())
                .dataDePublicacao(livroRequestBody.getDataDePublicacao())
                .capa(livroRequestBody.getCapa())
                .estoque(livroRequestBody.getEstoque())
                .build();
    }

    public static Livro toLivroComAutorECategoria(LivroRequest livroRequestBody, Categoria categoria, Autor autor) {
        return Livro.builder()
                .categoria(categoria)
                .autor(autor)
                .titulo(livroRequestBody.getTitulo())
                .descricao(livroRequestBody.getDescricao())
                .sumario(livroRequestBody.getSumario())
                .preco(livroRequestBody.getPreco())
                .paginas(livroRequestBody.getPaginas())
                .isbn(livroRequestBody.getIsbn())
                .dataDePublicacao(livroRequestBody.getDataDePublicacao())
                .capa(livroRequestBody.getCapa())
                .estoque(livroRequestBody.getEstoque())
                .build();
    }

    public static LivroResponse toLivroComAutorECategoriaResponse(Livro livro) {
        return LivroResponse.builder()
                .id(livro.getId())
                .categoria(CategoriaResponse.builder()
                        .id(livro.getCategoria().getId())
                        .descricao(livro.getCategoria().getDescricao())
                        .nome(livro.getCategoria().getNome())
                        .build()
                )
                .autor(AutorResponse.builder()
                        .id(livro.getAutor().getId())
                        .nome(livro.getAutor().getNome())
                        .descricao(livro.getAutor().getDescricao())
                        .email(livro.getAutor().getEmail())
                        .build()
                )
                .titulo(livro.getTitulo())
                .descricao(livro.getDescricao())
                .sumario(livro.getSumario())
                .preco(livro.getPreco())
                .paginas(livro.getPaginas())
                .isbn(livro.getIsbn())
                .dataDePublicacao(livro.getDataDePublicacao())
                .capa(livro.getCapa())
                .estoque(livro.getEstoque())
                .build();
    }

    public static LivroPaginadoResponse toLivroPaginadoResponse(Page<LivroResponse> livroPaginadoResponseBody) {
        return LivroPaginadoResponse.builder()
                .totalPaginas(livroPaginadoResponseBody.getTotalPages())
                .livrosLista(livroPaginadoResponseBody.getContent())
                .build();
    }
}
