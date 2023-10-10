package tech.evanildodeveloper.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.evanildodeveloper.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
