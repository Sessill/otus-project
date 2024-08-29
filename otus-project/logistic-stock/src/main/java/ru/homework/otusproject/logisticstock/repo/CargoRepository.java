package ru.homework.otusproject.logisticstock.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.homework.otusproject.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
