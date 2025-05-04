package br.com.estudos.to_do_list.controller;

import br.com.estudos.to_do_list.dto.CadastroTarefaDTO;
import br.com.estudos.to_do_list.dto.DetalhamentoTarefaDTO;
import br.com.estudos.to_do_list.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTarefa(@RequestBody @Valid CadastroTarefaDTO cadastroTarefaDTO, UriComponentsBuilder uriBuilder) {

        DetalhamentoTarefaDTO detalhamentoTarefaDTO = tarefaService.cadastrarTarefa(cadastroTarefaDTO);

        var uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(detalhamentoTarefaDTO.id()).toUri();

        return ResponseEntity.created(uri).body(detalhamentoTarefaDTO);
    }
}
