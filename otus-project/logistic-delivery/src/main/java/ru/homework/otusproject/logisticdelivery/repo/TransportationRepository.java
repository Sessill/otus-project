package ru.homework.otusproject.logisticdelivery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.homework.otusproject.entity.Transportation;

import java.util.List;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Long> {

    @Query("select d.cargoId from Transportation d where d.isDamage = true")

    String findDamage();
}
