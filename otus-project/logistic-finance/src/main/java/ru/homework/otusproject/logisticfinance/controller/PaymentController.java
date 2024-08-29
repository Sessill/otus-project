package ru.homework.otusproject.logisticfinance.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.otusproject.entity.Payment;
import ru.homework.otusproject.logisticfinance.service.PaymentService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    public final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {this.paymentService = paymentService;}

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable("id") Long id) {
        Payment payment = null;
        try {
            payment = paymentService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/add")
    public ResponseEntity<Payment> add(@RequestBody Payment payment) {
        if (payment.getId() != null) {
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(paymentService.add(payment));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id) {
        Payment payment = paymentService.findById(id);
        if (payment.getId() == null) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        paymentService.update(payment);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            paymentService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
