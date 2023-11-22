package br.com.sup.api.models;

import br.com.sup.api.controllers.UserController;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rc_tb_usuarios")
@Builder
@Data
@SequenceGenerator(name = "user", sequenceName = "SQ_TB_USER", allocationSize = 1)
public class User implements UserDetails {

    @Column(name = "id_usuario")
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    private Long id;

    @Column(name = "nm_usuario", length = 80, nullable = false)
    @NotBlank @Size(max = 80)
    private String nome;

    @Column(name = "nr_age", nullable = false)
    private String age;

    @Column(name = "ds_email", length = 150, nullable = false)
    @Email
    private String email;

    @Column(name = "ds_senha", length = 255, nullable = false)
    @NotBlank
    private String senha;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HealthRegister> healthRegisters;

    public void addRegister(HealthRegister register) {
        this.healthRegisters.add(register);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public EntityModel<User> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(UserController.class).listAll(null)).withRel("all"),
                linkTo(methodOn(UserController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).delete(id)).withRel("delete")
        );
    }
}
