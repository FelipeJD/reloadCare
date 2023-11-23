package br.com.reloadCare.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reloadCare.api.models.HealthRegister;

import java.util.List;

public interface HealthRegisterRepository extends JpaRepository<HealthRegister, Long> {

    //List<HealthRegister> findByNomeContaining(String nome);

}
