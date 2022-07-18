package com.kimetsu.dto.response;

import com.kimetsu.domain.Endereco;
import com.kimetsu.domain.SatatusCompra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class CompraResponse {

        private Long numDoPedido;
        private String nomeDoCliente;
        private LocalDateTime dataPedido;
        private Integer quandidadeDeLivros;
        private String NomeDoLivro;
        private EnderecoResponse endereco;
        private BigDecimal valorDoPedido;
        private SatatusCompra status;
}
