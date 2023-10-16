package tech.evanildodeveloper.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.evanildodeveloper.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
