package br.com.reloadCare.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reloadCare.api.dto.HealthRegisterDto;
import br.com.reloadCare.api.models.HealthRegister;
import br.com.reloadCare.api.models.User;
import br.com.reloadCare.api.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HealthRegisterService registerService;

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("User with id " + id + " not found"));
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User with email " + email + " not found"));
    }

    public List<User> findAllByName(String name) {
        return repository.findByNomeContaining(name);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void addNewHealthRegister(Long id, HealthRegisterDto registerDto) {
        try {
            User user = findById(id);
            HealthRegister register = new HealthRegister(registerDto);
            register.setUser(user);
            registerService.saveRegister(register);
            user.addRegister(register);
           saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Long id, User user) {
        user.setId(id);
        saveUser(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
