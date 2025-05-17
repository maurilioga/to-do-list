package br.com.estudos.to_do_list.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioDTO(
        String login,

        String senha) {
}
