package br.com.estudos.to_do_list.validation;

import br.com.estudos.to_do_list.dto.CadastroUsuarioDTO;
import br.com.estudos.to_do_list.exception.ValidacaoException;
import br.com.estudos.to_do_list.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioUnicoValidation implements UsuarioValidation{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(CadastroUsuarioDTO cadastroUsuarioDTO) {

        if(usuarioRepository.existsByLoginIgnoreCase(cadastroUsuarioDTO.login())) {
            throw new ValidacaoException("Login informado já existe!");
        }
    }
}
