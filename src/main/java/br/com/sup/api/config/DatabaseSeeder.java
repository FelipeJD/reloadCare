package br.com.sup.api.config;

import br.com.sup.api.models.User;
import br.com.sup.api.repository.HealthRegisterRepository;
import br.com.sup.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HealthRegisterRepository healthRegisterRepository;

    @Override
    public void run(String... args) throws Exception {

        User user = User.builder()
                .nome("John Doe")
                .email("johndoe@example.com")
                .senha("password123")
                .build();

        userRepository.save(user);

        List<User> users = Arrays.asList(
                User.builder()
                        .nome("Jane Doe")
                        .email("janedoe@example.com")
                        .senha("password123")
                        .build(),
                User.builder()
                        .nome("Peter Smith")
                        .email("petersmith@example.com")
                        .senha("password123")
                        .build()
        );

        userRepository.saveAll(users);
    }

}
