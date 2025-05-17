package br.com.estudos.to_do_list.service;

import br.com.estudos.to_do_list.dto.CadastroUsuarioDTO;
import br.com.estudos.to_do_list.model.Usuario;
import br.com.estudos.to_do_list.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO) {

        String login = cadastroUsuarioDTO.login().trim();
        String senha = passwordEncoder.encode(cadastroUsuarioDTO.senha());

        Usuario usuario = new Usuario(login, senha);

        usuarioRepository.save(usuario);
    }
}
