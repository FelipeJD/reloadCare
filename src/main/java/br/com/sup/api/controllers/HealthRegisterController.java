package br.com.sup.api.controllers;

import br.com.sup.api.models.HealthRegister;
import br.com.sup.api.dto.HealthRegisterDto;
import br.com.sup.api.service.HealthRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registers")
public class HealthRegisterController {

    @Autowired
    HealthRegisterService registerService;

    @GetMapping
    public List<HealthRegister> listAll(@RequestParam(required = false) String nome) {
        return registerService.findAll();
    }

    @GetMapping("/{id}")
    public HealthRegister findById(@PathVariable Long id) {
        return registerService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthRegister> update(@PathVariable Long id, @RequestBody @Valid HealthRegisterDto healthRegisterDto){
        HealthRegister hr = registerService.updateRegister(id, healthRegisterDto);
        return ResponseEntity.ok(hr);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        registerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
