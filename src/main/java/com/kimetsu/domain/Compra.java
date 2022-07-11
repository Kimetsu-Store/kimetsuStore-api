package com.kimetsu.domain;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    private Livro livro;

    @Column(nullable = false)
    private Integer quandidadeDeLivros;

    @Column(nullable = false, length = 250)
    private String nomeCliente;

    @Column(nullable = false, length = 12)
    private String cpf;

    @Column(unique = true, nullable = false, length = 250)
    private String email;

    @Column(nullable = false)
    private BigDecimal valorTotalDoPedido;

    @Builder.Default
    @Column(name = "data_de_criacao")
    private LocalDateTime dataDoPedido = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco enderecoDeEntrega;

}