package com.kimetsu.controller;

import com.kimetsu.dto.request.AutorRequest;
import com.kimetsu.dto.response.AutorResponse;
import com.kimetsu.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin()
@RequestMapping("/livraria")
@RestController
public class AutorController {

        private final AutorService autorService;

        @Autowired
        public AutorController(AutorService autorService) {
            this.autorService = autorService;
        }
        /*
        public Controller(AutorService autorService) {
            this.autorService = autorService;
        }
        */


        @PostMapping("/autores")
        public ResponseEntity<AutorResponse> salvar(@Valid @RequestBody AutorRequest autorRequest) {
            return new ResponseEntity<>(autorService.salvar(autorRequest), HttpStatus.CREATED);
        }


        @GetMapping("/autores/{id}")
        public ResponseEntity<AutorResponse> getPorId(@PathVariable Long id) {
            return new ResponseEntity<>(autorService.buscaPorId(id), HttpStatus.OK);
        }

        @GetMapping("/autores")
        public ResponseEntity<List<AutorResponse>> buscarTodos() {
            return new ResponseEntity<>(autorService.buscarTodos(), HttpStatus.OK);
        }

        @GetMapping("/autores/nome/{nome}")
        public ResponseEntity<AutorResponse> getPorNome(@PathVariable String nome) {
            return new ResponseEntity<>(autorService.buscaPorNome(nome), HttpStatus.OK);
        }

        @PutMapping("/autores/{id}")
        public ResponseEntity<AutorResponse> atualizar(@Valid @RequestBody AutorRequest autorRequest,
                                                           @PathVariable Long id) {
            return new ResponseEntity<>(autorService.atualizar(autorRequest, id), HttpStatus.OK);
        }

        @DeleteMapping("/autores/{nome}")
        public ResponseEntity<Void> deletar(@PathVariable String nome) {
            autorService.deletar(nome);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }
