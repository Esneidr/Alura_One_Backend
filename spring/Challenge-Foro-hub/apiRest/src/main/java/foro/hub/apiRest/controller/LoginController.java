package foro.hub.apiRest.controller;

import foro.hub.apiRest.domain.DTO.ApiResponse;
import foro.hub.apiRest.domain.DTO.LoginDTO;
import foro.hub.apiRest.domain.DTO.TokenDTO;
import foro.hub.apiRest.domain.model.Usuario;
import foro.hub.apiRest.domain.response.ResponseBuilder;
import foro.hub.apiRest.infra.secuerity.TokenService;
import jakarta.validation.Valid;
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
public class LoginController {

    @Autowired
    private TokenService service;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<ApiResponse<TokenDTO>> iniciarSesion(
            @RequestBody @Valid LoginDTO data) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        data.email(),
                        data.password()
                );

        var autenticacion = manager.authenticate(authenticationToken);

        var tokenJWT = service.generarToken(
                (Usuario) autenticacion.getPrincipal()
        );

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Inicio de sesión exitoso.",
                        new TokenDTO(tokenJWT)
                )
        );
    }
}
