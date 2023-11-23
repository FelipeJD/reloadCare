package br.com.sup.api.dto;
import br.com.sup.api.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDTO {

    private String nome;

    private String age;

    private String email;

    private String senha;

    public UserUpdateDTO(User user) {
        this.nome = user.getNome();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.senha = user.getPassword();
    }

}
