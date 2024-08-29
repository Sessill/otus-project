package ru.homework.otusproject.logistictransport.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.otusproject.entity.Transport;
import ru.homework.otusproject.logistictransport.repo.TransportRepository;

@Service
@Transactional
public class TransportService {

    public final TransportRepository repository;

    public TransportService(TransportRepository repository) {this.repository = repository;}

    public Transport add(Transport transport) {return repository.save(transport);}

    public Transport update(Transport transport) {
        return repository.save(transport);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Transport findById(Long id) {return repository.findById(id).get();}

    public Transport findByParams(Long count) {return repository.findByParams(count); }
}
