package com.kimetsu.dto.request;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LivroRequest {

    @NotNull
    private Long categoria_id;

    @NotNull
    private Long autor_id;

    @Size(max = 255, message = "Campo String Ultrapassou o limite de caracteres")
    @NotEmpty(message = "Campo Título inválido")
    private String titulo;

    @NotNull(message = "Campo Sumário inválido")
    private String sumario;

    @NotEmpty(message = "Campo Descrição inválido")
    private String descricao;

    @DecimalMin(value = "0", message = "Campo Preço negativo")
    @NotNull(message = "Campo Preço inválido")
    private BigDecimal preco;

    @Positive(message = "Campo Páginas negativo")
    @NotNull(message = "Campo Páginas inválido")
    private int paginas;

    @Size(max = 21, message = "Campo Isbn Ultrapassou o limite de caracteres")
    @NotNull(message = "Campo Isbn inválido")
    @ISBN(type = ISBN.Type.ANY)
    @Schema(description = "ISBN 13 digitos", example = "9788543105291", required = true)
    private String isbn;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @NotNull(message = "Campo Datas inválido")
    @Schema(description = "Data Criação da primeira publicação", example = "2022-03-30", required = true)
    private LocalDate dataDePublicacao;

    @NotEmpty(message = "Campo Capa inválido")
    @Schema(description = "URL da imagem da capa",
            example = "http://books.google.com/books/content?id=pCbhDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            required = true)
    private String capa;

    @NotNull(message = "Campo Estoque inválido")
    @Schema(description = "Quantidade deve ser > ZERO < 100", example = "100", required = true)
    private int estoque;
}
