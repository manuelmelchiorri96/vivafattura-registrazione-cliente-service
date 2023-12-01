package com.vivafattura.controller;

import com.vivafattura.dto.SignupDTO;
import com.vivafattura.service.RegistrazioneClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/api")
public class ClienteRegistrazioneController {

    private final RegistrazioneClienteService registrazioneClienteService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
        return registrazioneClienteService.createUser(signupDTO);
    }


}
