package com.kimetsu.infra;



import com.kimetsu.domain.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Page<Livro> findAllByTituloContainsOrAutorNomeContainsAllIgnoreCase(Pageable pageable, String titulo, String nomeAutor);
    Page<Livro> findAllByCategoriaNome(Pageable pageable, String nome);
}
