package br.com.estudos.to_do_list.controller;

import br.com.estudos.to_do_list.dto.AtualizaTarefaDTO;
import br.com.estudos.to_do_list.dto.CadastroTarefaDTO;
import br.com.estudos.to_do_list.dto.DetalhamentoTarefaDTO;
import br.com.estudos.to_do_list.model.Tarefa;
import br.com.estudos.to_do_list.repository.TarefaRepository;
import br.com.estudos.to_do_list.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTarefa(@RequestBody @Valid CadastroTarefaDTO cadastroTarefaDTO, UriComponentsBuilder uriBuilder) {

        DetalhamentoTarefaDTO detalhamentoTarefaDTO = tarefaService.cadastrarTarefa(cadastroTarefaDTO);

        var uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(detalhamentoTarefaDTO.id()).toUri();

        return ResponseEntity.created(uri).body(detalhamentoTarefaDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DetalhamentoTarefaDTO>> listarTarefas(@PageableDefault(size = 5, sort = {"prioridade", "prazoFinal"}) Pageable pageable) {

        Page<DetalhamentoTarefaDTO> tarefaDTO = tarefaRepository.findAllByAtivaTrue(pageable).map(DetalhamentoTarefaDTO::new);

        return ResponseEntity.ok(tarefaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoTarefaDTO> buscarTarefa(@PathVariable Long id) {

        Tarefa tarefa = tarefaRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoTarefaDTO(tarefa));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoTarefaDTO> atualizarTarefa(@RequestBody AtualizaTarefaDTO atualizaTarefaDTO) {

        Tarefa tarefa = tarefaService.atualizarTarefa(atualizaTarefaDTO);

        return ResponseEntity.ok(new DetalhamentoTarefaDTO(tarefa));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhamentoTarefaDTO> concluirTarefa(@PathVariable Long id) {

        Tarefa tarefa = tarefaService.concluirTarefa(id);

        return ResponseEntity.ok(new DetalhamentoTarefaDTO(tarefa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTarefa(@PathVariable Long id) {

        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
