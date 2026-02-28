package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.DTO.UsuarioDTO;
import med.voll.api.domain.model.Usuario;
import med.voll.api.infra.security.TokenDTO;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private TokenService service;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid UsuarioDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var autenticacion = manager.authenticate(token);

        var tokenJWT = service.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

}
