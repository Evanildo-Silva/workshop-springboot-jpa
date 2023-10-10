package tech.evanildodeveloper.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.evanildodeveloper.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
