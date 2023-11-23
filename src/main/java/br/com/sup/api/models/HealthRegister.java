package br.com.sup.api.models;

import br.com.sup.api.dto.HealthRegisterDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "rc_tb_health")
public class HealthRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String health;

    @NotBlank
    @Column(nullable = false)
    private String mentalHealth;

    @NotBlank
    @Column(nullable = false)
    private String substances;

    @NotBlank
    @Column(nullable = false)
    private String substanceFrequencies;

    @NotBlank
    @Column(nullable = false)
    private String goals;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_user")
    private User user;

    public HealthRegister(HealthRegisterDto healthRegisterDto) {
        this.health = healthRegisterDto.health();
        this.mentalHealth = healthRegisterDto.mentalHealth();
        this.substances = healthRegisterDto.substances();
        this.substanceFrequencies = healthRegisterDto.substanceFrequencies();
        this.goals = healthRegisterDto.goals();
    }

}
