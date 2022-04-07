package com.kimetsu.controller;

import com.kimetsu.dto.request.CategoriaRequest;
import com.kimetsu.dto.response.CategoriaResponse;
import com.kimetsu.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin()
@RequestMapping("/livraria")
@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponse> salvar(@Valid @RequestBody CategoriaRequest categoriaRequest) {
        return new ResponseEntity<>(categoriaService.salvar(categoriaRequest), HttpStatus.CREATED);
    }


    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponse> getPorId(@PathVariable Long id) {
        return new ResponseEntity<>(categoriaService.buscaPorId(id), HttpStatus.OK);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaResponse>> buscarTodos() {
        return new ResponseEntity<>(categoriaService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/categorias/nome/{nome}")
    public ResponseEntity<CategoriaResponse> getPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(categoriaService.buscaPorNome(nome), HttpStatus.OK);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponse> atualizar(@Valid @RequestBody CategoriaRequest categoriaRequest,
                                                           @PathVariable Long id) {
        return new ResponseEntity<>(categoriaService.atualizar(categoriaRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/categorias/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome) {
        categoriaService.deletar(nome);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
