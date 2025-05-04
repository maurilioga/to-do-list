package br.com.estudos.to_do_list.service;

import br.com.estudos.to_do_list.dto.CadastroTarefaDTO;
import br.com.estudos.to_do_list.dto.DetalhamentoTarefaDTO;
import br.com.estudos.to_do_list.model.Tarefa;
import br.com.estudos.to_do_list.repository.TarefaRepository;
import br.com.estudos.to_do_list.validation.TarefaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private List<TarefaValidation> tarefaValidations = new ArrayList<>();

    public DetalhamentoTarefaDTO cadastrarTarefa(CadastroTarefaDTO cadastroTarefaDTO){

        tarefaValidations.forEach(v -> v.validar(cadastroTarefaDTO));

        Tarefa tarefa = new Tarefa(cadastroTarefaDTO);
        tarefaRepository.save(tarefa);

        return new DetalhamentoTarefaDTO(tarefa);
    }
}
