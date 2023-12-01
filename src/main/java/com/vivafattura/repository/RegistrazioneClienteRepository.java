package com.vivafattura.repository;

import com.vivafattura.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrazioneClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByUsername(String username);
}
