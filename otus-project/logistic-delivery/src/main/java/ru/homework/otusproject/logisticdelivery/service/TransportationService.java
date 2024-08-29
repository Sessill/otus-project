package ru.homework.otusproject.logisticdelivery.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.otusproject.entity.Transportation;
import ru.homework.otusproject.logisticdelivery.repo.TransportationRepository;

import java.util.List;

@Service
@Transactional
public class TransportationService {

    public final TransportationRepository repository;

    public TransportationService(TransportationRepository repository) {this.repository = repository;}

    public Transportation add(Transportation transportation) {
        return repository.save(transportation);
    }

    public Transportation update(Transportation transportation) {
        return repository.save(transportation);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Transportation findById(Long id) {
        return repository.findById(id).get();
    }

    public String findDamage() {return repository.findDamage();}
}
