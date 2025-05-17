package br.com.estudos.to_do_list.controller;

import br.com.estudos.to_do_list.dto.CadastroUsuarioDTO;
import br.com.estudos.to_do_list.dto.DadosTokenJWT;
import br.com.estudos.to_do_list.dto.LoginUsuarioDTO;
import br.com.estudos.to_do_list.model.Usuario;
import br.com.estudos.to_do_list.service.TokenService;
import br.com.estudos.to_do_list.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid CadastroUsuarioDTO cadastroUsuarioDTO) {

        usuarioService.cadastrarUsuario(cadastroUsuarioDTO);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity loginUsuario(@RequestBody LoginUsuarioDTO loginUsuarioDTO) {

        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuarioDTO.login(), loginUsuarioDTO.senha()));
        String tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
