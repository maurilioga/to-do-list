package br.com.estudos.to_do_list.model;

import br.com.estudos.to_do_list.dto.CadastroTarefaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TAREFA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @Enumerated(EnumType.STRING)
    private PrioridadeTarefa prioridade;

    private LocalDateTime dataCriacao;

    private LocalDateTime prazoFinal;

    private LocalDateTime dataConclusao;

    private Boolean ativa;

    public Tarefa(CadastroTarefaDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.nome();
        this.status = StatusTarefa.PENDENTE;
        this.prioridade = dto.prioridade();
        this.prazoFinal = dto.prazoFinal();
        this.dataCriacao = LocalDateTime.now();
        this.ativa = true;
    }
}
