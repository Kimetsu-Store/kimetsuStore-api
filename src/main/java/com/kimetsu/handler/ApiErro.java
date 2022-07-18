package com.kimetsu.handler;

import com.kimetsu.exception.dto.ApiErroDetalhe;

import java.util.List;

public record ApiErro(String mensagem, List<ApiErroDetalhe> erros) {
}