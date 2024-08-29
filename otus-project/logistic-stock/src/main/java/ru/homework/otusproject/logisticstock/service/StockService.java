package ru.homework.otusproject.logisticstock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.otusproject.entity.Stock;
import ru.homework.otusproject.logisticstock.repo.StockRepository;

@Service
@Transactional
public class StockService {

    public final StockRepository repository;

    public StockService(StockRepository repository) {this.repository = repository;}

    public Stock add(Stock stock) {
        return repository.save(stock);
    }

    public Stock update(Stock stock) {
        return repository.save(stock);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Stock findById(Long id) {
        return repository.findById(id).get();
    }
}
