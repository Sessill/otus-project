package ru.homework.otusproject.logisticclaim.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.homework.otusproject.entity.Claims;

@Repository
public interface ClaimRepository extends JpaRepository<Claims, Long> {
}
