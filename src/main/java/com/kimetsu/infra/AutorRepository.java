package com.kimetsu.infra;
import com.kimetsu.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long>{

    Optional<Autor> findByNome(String nome);
}



