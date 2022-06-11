package com.kimetsu.controller;

import com.kimetsu.dto.request.LivroRequest;
import com.kimetsu.dto.response.LivroPaginadoResponse;
import com.kimetsu.dto.response.LivroResponse;
import com.kimetsu.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin()
@RequestMapping("/livraria")
@RestController
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService iLivroService) {
        this.livroService = iLivroService;
    }

    @PostMapping("/livros")
    public ResponseEntity<LivroResponse> salvar(@Valid @RequestBody LivroRequest livroRequest) {
        return new ResponseEntity<>(livroService.salvar(livroRequest), HttpStatus.CREATED);
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<LivroResponse> getPorId(@PathVariable Long id) {
        return new ResponseEntity<>(livroService.buscaPorId(id), HttpStatus.OK);
    }

    @GetMapping("/livros/")
    public ResponseEntity<LivroPaginadoResponse> getPagina(@RequestParam(required = false) String palavra,
                                                           @PageableDefault(sort = "titulo",
                                                                       direction = Sort.Direction.ASC,
                                                                       page = 0,
                                                                       size = 2) Pageable pageable) {
        return new ResponseEntity<>(livroService.buscaPaginada(pageable, palavra), HttpStatus.OK);
    }

    @PutMapping("/livros/{id}")
    public ResponseEntity<LivroResponse> atualizar(@Valid @RequestBody LivroRequest livroRequest,
                                                       @PathVariable Long id) {
        return new ResponseEntity<>(livroService.atualizar(livroRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/livros/categoria")
    public ResponseEntity<LivroPaginadoResponse> getPaginaPorCategoria(@RequestParam String categoria,
                                                                           @PageableDefault(sort = "titulo",
                                                                                   direction = Sort.Direction.ASC,
                                                                                   page = 0,
                                                                                   size = 4) Pageable pageable) {
        return new ResponseEntity<>(livroService.buscaPaginadaPorCategoria(pageable, categoria), HttpStatus.OK);
    }
}
