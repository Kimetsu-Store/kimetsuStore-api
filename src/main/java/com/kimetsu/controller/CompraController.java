package com.kimetsu.controller;

import com.kimetsu.dto.request.CompraRequest;
import com.kimetsu.dto.response.CompraResponse;
import com.kimetsu.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin()
@RequestMapping("/livraria")
@RestController
public class CompraController {

    private final CompraService service;

    @Autowired
    public CompraController(CompraService service) {
        this.service = service;
    }

    @PostMapping("/compra")
    public ResponseEntity<CompraResponse> salvar(@Valid @RequestBody CompraRequest compraRequest) {
        return new ResponseEntity<>(service.salvar(compraRequest), HttpStatus.CREATED);
    }

    @GetMapping("/compras")
    public ResponseEntity<List<CompraResponse>> buscarTodos() {
        return new ResponseEntity<>(service.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/compras/{id}")
    public ResponseEntity<CompraResponse> getPorId(@PathVariable Long id) {
        return new ResponseEntity<>(service.buscaPorId(id), HttpStatus.OK);
    }

}
