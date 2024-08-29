package ru.homework.otusproject.logisticclaim.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.otusproject.entity.Claims;
import ru.homework.otusproject.logisticclaim.repo.ClaimRepository;

@Service
@Transactional
public class ClaimService {

    public final ClaimRepository repository;

    public ClaimService(ClaimRepository repository) {this.repository = repository;}

    public Claims add(Claims claims) {
        return repository.save(claims);
    }

    public Claims update(Claims claims) {
        return repository.save(claims);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Claims findById(Long id) {
        return repository.findById(id).get();
    }
}
