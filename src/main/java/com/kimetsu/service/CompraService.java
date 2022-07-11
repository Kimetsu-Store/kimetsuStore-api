package com.kimetsu.service;

import com.kimetsu.domain.Compra;
import com.kimetsu.domain.Endereco;
import com.kimetsu.domain.Livro;
import com.kimetsu.dto.request.CompraRequest;
import com.kimetsu.dto.response.CompraResponse;
import com.kimetsu.infra.CompraRepository;
import com.kimetsu.infra.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static com.kimetsu.mapper.CompraMapper.toCompra;
import static com.kimetsu.mapper.CompraMapper.toResponse;

@Service
public class CompraService {

    private final LivroService livroService;
    private final CompraRepository compraRepository;
    private final EnderecoRepository enderecoRepository;


    @Autowired
    public CompraService(LivroService livroService,
                         CompraRepository compraRepository,
                         EnderecoRepository enderecoRepository) {
        this.livroService = livroService;
        this.compraRepository = compraRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public CompraResponse salvar(CompraRequest request) {

        final Livro livro = livroService.buscaLivroPorId(request.getIdLivro());

        try {
            final Endereco endereco = this.salvarEndereco(request);
            final BigDecimal valorDaCompra = this.calcularValorDaCopra(livro.getPreco(), request.getQuandidadeDeLivros());
            final Compra compra = toCompra(request, endereco, livro, valorDaCompra);
            return toResponse(compraRepository.save(compra));

        } catch (RuntimeException e) {
            new Exception("Erro Interno da API, Verifique os dados repassados para serem salvos", e);
        }
        return null;
    }

    private BigDecimal calcularValorDaCopra(BigDecimal valorUnitario, Integer quantidade) {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    private Endereco salvarEndereco(CompraRequest request) throws RuntimeException {
        Endereco endereco = Endereco.builder()
                .bairro(request.getBairro())
                .cep(request.getCep())
                .complemento(request.getComplemento())
                .estado(request.getBairro())
                .cidade(request.getCidade())
                .pais(request.getPais())
                .rua(request.getRua())
                .numero(request.getNumeroRua())
                .build();
        return enderecoRepository.save(endereco);
    }

}
