package com.vivafattura.service;

import com.vivafattura.dto.SignupDTO;
import org.springframework.http.ResponseEntity;

public interface RegistrazioneClienteService {

    public ResponseEntity<?> createUser(SignupDTO signupDTO);

}
