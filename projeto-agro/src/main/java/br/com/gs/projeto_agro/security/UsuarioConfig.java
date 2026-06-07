package br.com.gs.projeto_agro.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.gs.projeto_agro.model.Usuario;
import br.com.gs.projeto_agro.repository.UsuarioRepository;

@Configuration
public class UsuarioConfig {

    private final UsuarioRepository repo;

    public UsuarioConfig(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return email -> {

            Usuario usuario = repo.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getSenha()) // já vem BCRYPT do banco
                    .roles("USER")
                    .build();
        };
    }
}