package ru.homework.otusproject.logisticorders.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.homework.otusproject.entity.Order;
import ru.homework.otusproject.logisticorders.service.OrderService;
import ru.homework.otusproject.utils.rest.webclient.CargoWebClientBuilder;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/order")
public class OrderController {
    public final OrderService orderService;
    private CargoWebClientBuilder cargoWebClientBuilder;

    public OrderController(OrderService orderService, CargoWebClientBuilder cargoWebClientBuilder) {
        this.orderService = orderService;
        this.cargoWebClientBuilder = cargoWebClientBuilder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id) {
        Order order = null;
        try {
            order = orderService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping("/add")
    public ResponseEntity<Order> add(@RequestBody Order order,@AuthenticationPrincipal Jwt jwt) {
        if (order.getId() != null) {
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (cargoWebClientBuilder.cargoExists(Long.valueOf(order.getCargoId()),jwt)) {
            return ResponseEntity.ok(orderService.add(order));
        }
        return new ResponseEntity("cargo id not found", HttpStatus.NOT_ACCEPTABLE);

    }

    @PutMapping("/update")
    public ResponseEntity<Order> update(@RequestBody Order order) {

        if (order.getId() == null) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        orderService.update(order);

        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            orderService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
