package com.kimetsu.mapper;

import com.kimetsu.domain.Compra;
import com.kimetsu.domain.Endereco;
import com.kimetsu.domain.Livro;
import com.kimetsu.dto.request.CompraRequest;
import com.kimetsu.dto.response.CompraResponse;
import com.kimetsu.dto.response.EnderecoResponse;

import java.math.BigDecimal;

public abstract class CompraMapper {

    public static Compra toCompra(CompraRequest CompraRequestBody, Endereco endereco, Livro livro,  BigDecimal  valorTotalPedido ) {
        return Compra.builder()
                .cpf(CompraRequestBody.getCpf())
                .email(CompraRequestBody.getEmail())
                .enderecoDeEntrega(endereco)
                .livro(livro)
                .quandidadeDeLivros(CompraRequestBody.getQuandidadeDeLivros())
                .valorTotalDoPedido(valorTotalPedido)
                .build();
    }
    public static CompraResponse toResponse(Compra compra){
        return CompraResponse.builder()
                .numDoPedido(compra.getId())
                .NomeDoLivro(compra.getLivro().getTitulo())
                .nomeDoCliente(compra.getNomeCliente())
                .endereco( EnderecoResponse.builder()
                        .bairro(compra.getEnderecoDeEntrega().getBairro())
                        .cep(compra.getEnderecoDeEntrega().getCep())
                        .cidade(compra.getEnderecoDeEntrega().getCidade())
                        .complemento(compra.getEnderecoDeEntrega().getComplemento())
                        .estado(compra.getEnderecoDeEntrega().getEstado())
                        .rua(compra.getEnderecoDeEntrega().getRua())
                        .numero(compra.getEnderecoDeEntrega().getNumero())
                        .build())
                .dataPedido(compra.getDataDoPedido())
                .valorDoPedido(compra.getValorTotalDoPedido())
                .quandidadeDeLivros(compra.getQuandidadeDeLivros())
                .build();
    }

}
