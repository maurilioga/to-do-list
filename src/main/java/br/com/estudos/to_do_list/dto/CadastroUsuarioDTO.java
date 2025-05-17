package br.com.estudos.to_do_list.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastroUsuarioDTO(
        @NotBlank
        @Size(min = 3, max = 50)
        @Pattern(regexp = "^[a-zA-Z0-9_.-]{3,50}$", message = "Login deve conter apenas letras, números, ponto, underline ou hífen.")
        String login,

        @NotBlank
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
                message = "A senha deve conter ao menos 8 caracteres, incluindo letra maiúscula, minúscula, número e símbolo.")
        String senha) {
}
