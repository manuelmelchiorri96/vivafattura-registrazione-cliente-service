package com.vivafattura.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupDTO {


    private String nome;
    private String cognome;
    private String codiceFiscale;
    private LocalDate dataDiNascita;
    private String indirizzo;
    private String cap;
    private String email;
    private String numeroDiTelefono;
    private String username;
    private String password;

}
