package ru.homework.otusproject.logisticorders.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.homework.otusproject.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
