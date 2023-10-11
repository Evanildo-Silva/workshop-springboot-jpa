package tech.evanildodeveloper.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.evanildodeveloper.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
