package ru.homework.otusproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "stock", schema = "cargo_stock", catalog = "stock")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stock {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = -1)
    private Long id;
    @Basic
    @Column(name = "cargo_id", nullable = false, length = -1)
    private Long cargoId;
    @Basic
    @Column(name = "count", nullable = false)
    private long count;
    @Basic
    @Column(name = "direction", nullable = false)
    private long direction;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return count == stock.count && direction == stock.direction && Objects.equals(id, stock.id) && Objects.equals(cargoId, stock.cargoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cargoId, count, direction);
    }
}
