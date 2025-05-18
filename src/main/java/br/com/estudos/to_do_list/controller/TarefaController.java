package br.com.estudos.to_do_list.controller;

import br.com.estudos.to_do_list.dto.AtualizaTarefaDTO;
import br.com.estudos.to_do_list.dto.CadastroTarefaDTO;
import br.com.estudos.to_do_list.dto.DetalhamentoTarefaDTO;
import br.com.estudos.to_do_list.model.Tarefa;
import br.com.estudos.to_do_list.repository.TarefaRepository;
import br.com.estudos.to_do_list.service.TarefaService;
import br.com.estudos.to_do_list.service.TokenService;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTarefa(@RequestBody @Valid CadastroTarefaDTO cadastroTarefaDTO, @RequestHeader String authorization, UriComponentsBuilder uriBuilder) {

        String login = tokenService.getSubject(authorization);

        DetalhamentoTarefaDTO detalhamentoTarefaDTO = tarefaService.cadastrarTarefa(cadastroTarefaDTO, login);

        var uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(detalhamentoTarefaDTO.id()).toUri();

        return ResponseEntity.created(uri).body(detalhamentoTarefaDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DetalhamentoTarefaDTO>> listarTarefas(@PageableDefault(size = 5, sort = {"prioridade", "prazoFinal"}) Pageable pageable, @RequestHeader String authorization) {

        String login = tokenService.getSubject(authorization);

        Page<DetalhamentoTarefaDTO> tarefaDTO = tarefaService.listarTarefas(pageable, login);

        return ResponseEntity.ok(tarefaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoTarefaDTO> buscarTarefa(@PathVariable Long id, @RequestHeader String authorization) {

        String login = tokenService.getSubject(authorization);

        Tarefa tarefa = tarefaService.buscarTarefa(id, login);

        return ResponseEntity.ok(new DetalhamentoTarefaDTO(tarefa));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoTarefaDTO> atualizarTarefa(@RequestBody AtualizaTarefaDTO atualizaTarefaDTO, @RequestHeader String authorization) {

        String login = tokenService.getSubject(authorization);

        Tarefa tarefa = tarefaService.atualizarTarefa(atualizaTarefaDTO, login);

        return ResponseEntity.ok(new DetalhamentoTarefaDTO(tarefa));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhamentoTarefaDTO> concluirTarefa(@PathVariable Long id, @RequestHeader String authorization) {

        String login = tokenService.getSubject(authorization);

        Tarefa tarefa = tarefaService.concluirTarefa(id, login);

        return ResponseEntity.ok(new DetalhamentoTarefaDTO(tarefa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTarefa(@PathVariable Long id, @RequestHeader String authorization) {

        String login = tokenService.getSubject(authorization);

        tarefaService.excluirTarefa(id, login);
        return ResponseEntity.noContent().build();
    }
}
