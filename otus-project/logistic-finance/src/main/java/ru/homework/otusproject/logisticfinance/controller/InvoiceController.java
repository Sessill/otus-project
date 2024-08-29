package ru.homework.otusproject.logisticfinance.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.otusproject.entity.Invoice;
import ru.homework.otusproject.logisticfinance.service.InvoiceService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    public final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {this.invoiceService = invoiceService;}

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable("id") Long id) {
        Invoice invoice = null;
        try {
            invoice = invoiceService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(invoice);
    }

    @PostMapping("/add")
    public ResponseEntity<Invoice> add(@RequestBody Invoice invoice) {
        if (invoice.getId() != null) {
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(invoiceService.add(invoice));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id) {
        Invoice invoice = invoiceService.findById(id);
        if (invoice.getId() == null) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        invoiceService.update(invoice);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            invoiceService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
