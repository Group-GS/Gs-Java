package br.com.gs.projeto_agro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gs.projeto_agro.model.Usuario;
import br.com.gs.projeto_agro.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario salvar(Usuario usuario) {

        usuario.setSenha(encoder.encode(usuario.getSenha()));

        return repository.save(usuario);
    }
}