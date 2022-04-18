package com.kimetsu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 25)
    private String nome;

    @Column(unique = true, nullable = false, length = 35)
    @Pattern(regexp = ".+@.+\\\\.```a-z+")
    private String email;

    @Column(nullable= true, length = 200)
    private String descricao;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    @Column(nullable=false)
    private LocalDateTime dataCriacao;


}
