package br.com.estudos.to_do_list.repository;

import br.com.estudos.to_do_list.dto.DetalhamentoTarefaDTO;
import br.com.estudos.to_do_list.model.PrioridadeTarefa;
import br.com.estudos.to_do_list.model.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    boolean existsByNomeAndPrazoFinalAndAtivaIsTrue(String nome, LocalDateTime prazoFinal);

    Integer countByPrioridadeAndAtivaIsTrue(PrioridadeTarefa prioridade);

    Page<Tarefa> findAllByAtivaTrue(Pageable pageable);
}
