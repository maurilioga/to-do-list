package br.com.estudos.to_do_list.repository;

import br.com.estudos.to_do_list.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByLoginIgnoreCase(String login);

    UserDetails findByLoginIgnoreCase(String login);
}
