package br.com.gs.projeto_agro.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import br.com.gs.projeto_agro.security.JWTUtil;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email,
                                   @RequestParam String senha,
                                   @RequestParam(defaultValue = "10") Integer duracao) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senha)
            );

            String token = jwtUtil.gerarToken(email, duracao);

            return ResponseEntity.ok(token);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}