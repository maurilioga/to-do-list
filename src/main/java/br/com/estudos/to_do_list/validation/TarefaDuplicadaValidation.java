package br.com.estudos.to_do_list.validation;

import br.com.estudos.to_do_list.dto.CadastroTarefaDTO;
import br.com.estudos.to_do_list.exception.ValidacaoException;
import br.com.estudos.to_do_list.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarefaDuplicadaValidation implements TarefaValidation{

    @Autowired
    private TarefaRepository tarefaRepository;

    public void validar(CadastroTarefaDTO cadastroTarefaDTO) {

        boolean isTarefaDuplicada = tarefaRepository.existsByNomeAndPrazoFinalAndAtivaIsTrue(cadastroTarefaDTO.nome(), cadastroTarefaDTO.prazoFinal());

        if(isTarefaDuplicada) {
            throw new ValidacaoException("Tarefa duplicada! Existe outra tarefa com esse nome para o mesmo prazo.");
        }
    }
}
