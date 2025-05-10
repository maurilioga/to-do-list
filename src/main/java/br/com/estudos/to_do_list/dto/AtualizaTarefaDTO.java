package br.com.estudos.to_do_list.dto;

import br.com.estudos.to_do_list.model.PrioridadeTarefa;
import br.com.estudos.to_do_list.model.StatusTarefa;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AtualizaTarefaDTO (
        @NotNull
        Long id,

        String nome,

        String descricao,

        StatusTarefa status,

        PrioridadeTarefa prioridade,

        LocalDateTime prazoFinal){
}
