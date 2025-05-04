package br.com.estudos.to_do_list.dto;

import br.com.estudos.to_do_list.model.PrioridadeTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastroTarefaDTO (
        @NotBlank
        String nome,

        String descricao,

        PrioridadeTarefa prioridade,

        @NotNull
        LocalDateTime prazoFinal){
}
