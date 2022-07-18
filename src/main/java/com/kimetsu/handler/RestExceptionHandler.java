package com.kimetsu.handler;

import com.kimetsu.exception.NotFoundException;
import com.kimetsu.exception.dto.ApiErroDetalhe;
import com.kimetsu.exception.dto.NotFoundExceptionDetails;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> handlerNotFoundException(ChangeSetPersister.NotFoundException exception) {
        return new ResponseEntity<>(
                NotFoundExceptionDetails.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .titulo("Not Found Exception")
                        .detalhe(exception.getMessage())
                        .mensagem("Cheque a documentação da API")
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ApiErroDetalhe> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(p -> new ApiErroDetalhe(p.getField(), p.getDefaultMessage())).toList();

        final String mensagemErroValidacao = "Erro na validação";
        final ApiErro apiErro = new ApiErro(mensagemErroValidacao, errors);
        return ResponseEntity.badRequest().body(apiErro);
    }
}
