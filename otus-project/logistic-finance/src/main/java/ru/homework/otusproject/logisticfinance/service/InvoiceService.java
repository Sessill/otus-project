package ru.homework.otusproject.logisticfinance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.otusproject.entity.Invoice;
import ru.homework.otusproject.logisticfinance.repo.InvoiceRepository;

@Service
@Transactional
public class InvoiceService {

    public final InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {this.repository = repository;}

    public Invoice add(Invoice invoice) {
        return repository.save(invoice);
    }

    public Invoice update(Invoice invoice) {
        return repository.save(invoice);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Invoice findById(Long id) {
        return repository.findById(id).get();
    }
}
