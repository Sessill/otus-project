package ru.homework.otusproject.logisticorders.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.otusproject.entity.Order;
import ru.homework.otusproject.logisticorders.repo.OrderRepository;

@Service
@Transactional
public class OrderService {

    public final OrderRepository repository;

    public OrderService(OrderRepository repository) {this.repository = repository;}

    public Order add(Order order) {
        return repository.save(order);
    }

    public Order update(Order order) {
        return repository.save(order);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Order findById(Long id) {
        return repository.findById(id).get();
    }
}
