package br.com.vanessaeich.services;

import br.com.vanessaeich.dtos.v1.security.AccountCredencialsDTO;
import br.com.vanessaeich.dtos.v1.security.TokenDTO;
import br.com.vanessaeich.repositories.UserRepository;
import br.com.vanessaeich.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Vanessa Eich on 22/01/2023
 */
@Service
public class AuthServices {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private UserRepository repository;

    public AuthServices(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, UserRepository repository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    public ResponseEntity signin(AccountCredencialsDTO data){
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            var tokenResponse = new TokenDTO();
            if(user != null){
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + "not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e){
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    public ResponseEntity refreshToken(String username, String refreshToken){

            var user = repository.findByUsername(username);

            var tokenResponse = new TokenDTO();
            if(user != null){
                tokenResponse = tokenProvider.refreshToken(refreshToken);
            } else {
                throw new UsernameNotFoundException("Username " + username + "not found!");
            }
            return ResponseEntity.ok(tokenResponse);

    }
}
