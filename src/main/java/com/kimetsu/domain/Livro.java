package com.kimetsu.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @OneToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(nullable = false, length = 2500)
    private String descricao;

    private String sumario;

    @Column(nullable = false)
    private BigDecimal preco;

    private int paginas;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = true)
    private LocalDate dataDePublicacao;

    @Column(nullable = false)
    private String capa;

    private int estoque;
}
