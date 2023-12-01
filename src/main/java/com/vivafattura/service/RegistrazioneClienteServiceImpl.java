package com.vivafattura.service;

import com.vivafattura.dto.SignupDTO;
import com.vivafattura.entity.Cliente;
import com.vivafattura.repository.RegistrazioneClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistrazioneClienteServiceImpl implements RegistrazioneClienteService {


    private final RegistrazioneClienteRepository registrazioneClienteRepository;


    @Override
    public ResponseEntity<?> createUser(SignupDTO signupDTO) {

        Map<String, String> response = new HashMap<>();

        if (!isValidPassword(signupDTO.getPassword())) {
            response.put("message", "La password non soddisfa i criteri di complessità");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Cliente cliente = Cliente.builder()
                .nome(signupDTO.getNome())
                .cognome(signupDTO.getCognome())
                .codiceFiscale(signupDTO.getCodiceFiscale())
                .dataDiNascita(signupDTO.getDataDiNascita())
                .indirizzo(signupDTO.getIndirizzo())
                .cap(signupDTO.getCap())
                .email(signupDTO.getEmail())
                .numeroDiTelefono(signupDTO.getNumeroDiTelefono())
                .username(signupDTO.getUsername())
                .password(new BCryptPasswordEncoder().encode(signupDTO.getPassword()))
                .build();

        registrazioneClienteRepository.save(cliente);

        Cliente isClienteSignUp = registrazioneClienteRepository.findByUsername(cliente.getUsername());

        if (isClienteSignUp != null) {
            response.put("message", "Profilo creato!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        response.put("message", "Profilo non creato, riprova più tardi!");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    /** Aggiungi qui la logica per i criteri di complessità della password
     * Ad esempio, verifica la lunghezza, la presenza di lettere maiuscole, minuscole, numeri, ecc.
     * Restituisci true se la password è valida, altrimenti false.
     */
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*\\d.*");
    }


}
