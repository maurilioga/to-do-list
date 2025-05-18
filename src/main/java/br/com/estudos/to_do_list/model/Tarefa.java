package br.com.estudos.to_do_list.model;

import br.com.estudos.to_do_list.dto.AtualizaTarefaDTO;
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

    @ManyToOne
    private Usuario usuario;

    public Tarefa(CadastroTarefaDTO dto, Usuario usuario) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.status = StatusTarefa.PENDENTE;
        this.prioridade = dto.prioridade();
        this.prazoFinal = dto.prazoFinal();
        this.dataCriacao = LocalDateTime.now();
        this.ativa = true;
        this.usuario = usuario;
    }

    public void atualizar(AtualizaTarefaDTO atualizaTarefaDTO) {

        if(atualizaTarefaDTO.nome() != null) {
            this.nome = atualizaTarefaDTO.nome();
        }

        if(atualizaTarefaDTO.descricao() != null) {
            this.descricao = atualizaTarefaDTO.descricao();
        }

        if(atualizaTarefaDTO.status() != null) {
            this.status = atualizaTarefaDTO.status();
        }

        if(atualizaTarefaDTO.prioridade() != null) {
            this.prioridade = atualizaTarefaDTO.prioridade();
        }

        if(atualizaTarefaDTO.prazoFinal() != null) {
            this.prazoFinal = atualizaTarefaDTO.prazoFinal();
        }
    }

    public void concluir() {

        this.status = StatusTarefa.CONCLUIDA;
        this.dataConclusao = LocalDateTime.now();
        this.ativa = false;
    }

    public void excluir() {

        this.status = StatusTarefa.CANCELADA;
        this.ativa = false;
    }
}
