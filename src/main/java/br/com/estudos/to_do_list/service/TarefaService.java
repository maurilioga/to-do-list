package br.com.estudos.to_do_list.service;

import br.com.estudos.to_do_list.dto.AtualizaTarefaDTO;
import br.com.estudos.to_do_list.dto.CadastroTarefaDTO;
import br.com.estudos.to_do_list.dto.DetalhamentoTarefaDTO;
import br.com.estudos.to_do_list.model.Tarefa;
import br.com.estudos.to_do_list.model.Usuario;
import br.com.estudos.to_do_list.repository.TarefaRepository;
import br.com.estudos.to_do_list.validation.TarefaValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private List<TarefaValidation> tarefaValidations = new ArrayList<>();

    public DetalhamentoTarefaDTO cadastrarTarefa(CadastroTarefaDTO cadastroTarefaDTO, String login){

        Usuario usuario = validarUsuario(login);

        tarefaValidations.forEach(v -> v.validar(cadastroTarefaDTO));

        Tarefa tarefa = new Tarefa(cadastroTarefaDTO, usuario);
        tarefaRepository.save(tarefa);

        return new DetalhamentoTarefaDTO(tarefa);
    }

    public Tarefa atualizarTarefa(AtualizaTarefaDTO atualizaTarefaDTO, String login) {

        Usuario usuario = validarUsuario(login);

        Tarefa tarefa = tarefaRepository.findByIdAndUsuarioId(atualizaTarefaDTO.id(), usuario.getId());

        if(tarefa == null) {
            throw new EntityNotFoundException();
        }

        tarefa.atualizar(atualizaTarefaDTO);

        return tarefa;
    }

    public Tarefa concluirTarefa(Long id, String login) {

        Usuario usuario = validarUsuario(login);

        Tarefa tarefa = tarefaRepository.findByIdAndUsuarioId(id, usuario.getId());

        if(tarefa == null) {
            throw new EntityNotFoundException();
        }

        tarefa.concluir();

        return tarefa;
    }

    public void excluirTarefa(Long id, String login) {

        Usuario usuario = validarUsuario(login);

        Tarefa tarefa = tarefaRepository.findByIdAndUsuarioId(id, usuario.getId());

        if(tarefa == null) {
            throw new EntityNotFoundException();
        }

        tarefa.excluir();
    }

    public Page<DetalhamentoTarefaDTO> listarTarefas(Pageable pageable, String login) {

        Usuario usuario = validarUsuario(login);

        Page<Tarefa> listTarefas = tarefaRepository.findAllByAtivaTrueAndUsuarioId(pageable, usuario.getId());

        return listTarefas.map(DetalhamentoTarefaDTO::new);
    }

    public Tarefa buscarTarefa(Long id, String login) {

        Usuario usuario = validarUsuario(login);

        Tarefa tarefa = tarefaRepository.findByIdAndUsuarioId(id, usuario.getId());

        if(tarefa == null) {
            throw new EntityNotFoundException();
        }

        return tarefa;
    }

    private Usuario validarUsuario(String login) {

        Usuario usuario = usuarioService.buscarUsuario(login);

        if (usuario == null) {
            throw new RuntimeException();
        }

        return usuario;
    }
}
