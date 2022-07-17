package com.kimetsu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String nome;

    @Column(unique = true, nullable = false, length = 250)
    private String email;

    @Column(length = 2500)
    private String descricao;

    @Builder.Default
    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "data_de_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

}
