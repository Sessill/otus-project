package ru.homework.otusproject.logisticfinance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.otusproject.entity.Payment;
import ru.homework.otusproject.logisticfinance.repo.PaymentRepository;

@Service
@Transactional
public class PaymentService {

    public final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {this.repository = repository;}

    public Payment add(Payment payment) {
        return repository.save(payment);
    }

    public Payment update(Payment payment) {
        return repository.save(payment);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Payment findById(Long id) {
        return repository.findById(id).get();
    }
}
