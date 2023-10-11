package tech.evanildodeveloper.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.evanildodeveloper.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
