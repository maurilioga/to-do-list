package br.com.estudos.to_do_list.validation;

import br.com.estudos.to_do_list.dto.CadastroTarefaDTO;
import br.com.estudos.to_do_list.exception.ValidacaoException;
import br.com.estudos.to_do_list.model.PrioridadeTarefa;
import br.com.estudos.to_do_list.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LimiteTarefaPrioridadeAltaValidation implements TarefaValidation{

    @Autowired
    private TarefaRepository tarefaRepository;

    public void validar(CadastroTarefaDTO cadastroTarefaDTO) {

        Integer isAcimaLimiteTarefaPrioridadeAlta = tarefaRepository.countByPrioridadeAndAtivaIsTrue(PrioridadeTarefa.ALTA);

        if (isAcimaLimiteTarefaPrioridadeAlta > 5) {
            throw new ValidacaoException("Você possui muitas tarefas de alta prioridade! Resolva-as antes de adicionar novas tarefas.");
        }
    }
}
