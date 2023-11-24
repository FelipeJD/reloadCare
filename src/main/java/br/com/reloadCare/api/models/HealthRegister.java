package br.com.reloadCare.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reloadCare.api.dto.HealthRegisterDto;
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

    @Column(name = "id_health")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nm_health", nullable = false)
    private String health;

    @NotBlank
    @Column(name = "nm_mental_health", nullable = false)
    private String mentalHealth;

    @NotBlank
    @Column(name = "nm_substances", nullable = false)
    private String substances;

    @NotBlank
    @Column(name = "nm_substance_frequencies", nullable = false)
    private String substanceFrequencies;

    @NotBlank
    @Column(name = "nm_goals", nullable = false)
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
