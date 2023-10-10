package tech.evanildodeveloper.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tech.evanildodeveloper.course.entities.User;
import tech.evanildodeveloper.course.repositories.UserRepository;

@Configuration // Annotation indicating that it is a configuration class
@Profile("test")
// The CommandLineRunner interface executes
// everything contained in the run method when starting the application
public class TestConfig implements CommandLineRunner {

    @Autowired // Annotation to perform dependency injection
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}