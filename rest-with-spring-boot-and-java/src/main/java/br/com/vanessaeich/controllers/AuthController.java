package br.com.vanessaeich.controllers;

import br.com.vanessaeich.dtos.v1.PersonDTO;
import br.com.vanessaeich.dtos.v1.security.AccountCredencialsDTO;
import br.com.vanessaeich.model.User;
import br.com.vanessaeich.repositories.UserRepository;
import br.com.vanessaeich.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vanessa Eich on 22/01/2023
 */
@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServices authServices;

    public AuthController(AuthServices authServices, UserRepository repository) {
        this.authServices = authServices;
        this.repository = repository;
    }

    private final UserRepository repository;



    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredencialsDTO data){
        if(checkIfParamsIsNotNull(data)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        var token = authServices.signin(data);
        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        return token;
    }

    private static boolean checkIfParamsIsNotNull(AccountCredencialsDTO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank() ||
                data.getPassword() == null || data.getUsername().isBlank();
    }
}
