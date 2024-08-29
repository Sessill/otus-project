package ru.homework.otusproject.logistictransport.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.homework.otusproject.entity.Transport;


@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    @Query("SELECT t FROM  Transport  t WHERE t.item >= :count and t.isRepair = false ")
   Transport findByParams(@Param("count") Long count);
}
