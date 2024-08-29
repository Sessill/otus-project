package ru.homework.otusproject.logisticstock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.otusproject.entity.Cargo;
import ru.homework.otusproject.logisticstock.repo.CargoRepository;

@Service
@Transactional
public class CargoService {

    public final CargoRepository repository;

    public CargoService(CargoRepository repository) {this.repository = repository;}

    public Cargo add(Cargo cargo) {
        return repository.save(cargo);
    }

    public Cargo update(Cargo cargo) {
        return repository.save(cargo);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Cargo findById(Long id) {
        return repository.findById(id).get();
    }
}
