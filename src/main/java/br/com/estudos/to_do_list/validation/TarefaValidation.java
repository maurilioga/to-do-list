package br.com.estudos.to_do_list.validation;

import br.com.estudos.to_do_list.dto.CadastroTarefaDTO;

public interface TarefaValidation {

    void validar(CadastroTarefaDTO cadastroTarefaDTO);
}
