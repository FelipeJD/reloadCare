package br.com.sup.api.repository;

import br.com.sup.api.models.HealthRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthRegisterRepository extends JpaRepository<HealthRegister, Long> {

    //List<HealthRegister> findByNomeContaining(String nome);

}
