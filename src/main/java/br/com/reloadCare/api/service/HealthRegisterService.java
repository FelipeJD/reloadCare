package br.com.reloadCare.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reloadCare.api.dto.HealthRegisterDto;
import br.com.reloadCare.api.models.HealthRegister;
import br.com.reloadCare.api.repository.HealthRegisterRepository;

import java.util.List;

@Service
public class HealthRegisterService {

    @Autowired
    private HealthRegisterRepository registerRepository;

    public List<HealthRegister> findAll(){
        return registerRepository.findAll();
    }

    public HealthRegister findById(Long id) {
        return registerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Object with id " + id + " not found."));
    }

    public HealthRegister updateRegister(Long id, HealthRegisterDto healthRegisterDto) {
        HealthRegister hr = findById(id);

        if (healthRegisterDto.health() != null) {
             hr.setHealth(healthRegisterDto.health());
        }
        if (healthRegisterDto.mentalHealth() != null) {
            hr.setMentalHealth(healthRegisterDto.mentalHealth());
        }
        if (healthRegisterDto.substances() != null) {
            hr.setSubstances(healthRegisterDto.substances());
        }
        if (healthRegisterDto.substances() != null) {
            hr.setSubstanceFrequencies(healthRegisterDto.substanceFrequencies());
        }
        if (healthRegisterDto.goals() != null) {
            hr.setGoals(healthRegisterDto.goals());
        }

        return hr;
    }

    public void deleteById(Long id) {
        registerRepository.deleteById(id);
    }

    public void saveRegister(HealthRegister register) {
        registerRepository.save(register);
    }
}
