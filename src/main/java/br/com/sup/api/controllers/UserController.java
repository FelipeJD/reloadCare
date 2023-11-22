package br.com.sup.api.controllers;

import br.com.sup.api.models.Credencial;
import br.com.sup.api.models.HealthRegister;
import br.com.sup.api.dto.HealthRegisterDto;
import br.com.sup.api.models.User;
import br.com.sup.api.repository.UserRepository;
import br.com.sup.api.service.TokenService;
import br.com.sup.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @PostMapping("/registrar")
    public ResponseEntity<EntityModel<User>> register(@RequestBody @Valid User user) {
        user.setSenha(encoder.encode(user.getSenha()));
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.toEntityModel());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial) {
        manager.authenticate(credencial.toAuthentication());
        var user = userService.findByEmail(credencial.email());
        var token = tokenService.generateToken(credencial, user.getId());
        return ResponseEntity.ok(token);
    }

    @GetMapping
    public List<User> listAll(@RequestParam(required = false) String name) {
        if (name == null) {
            return userService.findAll();
        } else {
            return userService.findAllByName(name);
        }
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/{userId}/health")
    public ResponseEntity<HealthRegister> addRegister(@PathVariable Long userId,
                                                     @RequestBody @Valid HealthRegisterDto healthRegisterDto) {
        userService.addNewHealthRegister(userId, healthRegisterDto);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid User user) {
        try {
            userService.updateUser(id, user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
