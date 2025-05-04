package br.com.estudos.to_do_list.dto;

import br.com.estudos.to_do_list.model.PrioridadeTarefa;
import br.com.estudos.to_do_list.model.StatusTarefa;
import br.com.estudos.to_do_list.model.Tarefa;

import java.time.LocalDateTime;

public record DetalhamentoTarefaDTO (
        Long id,

        String nome,

        String descricao,

        StatusTarefa status,

        PrioridadeTarefa prioridade,

        LocalDateTime prazoFinal) {

    public DetalhamentoTarefaDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(), tarefa.getStatus(), tarefa.getPrioridade(), tarefa.getPrazoFinal());
    }
}
