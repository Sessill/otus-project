package ru.homework.otusproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.awt.print.Book;
import java.util.Objects;

@Entity
@Table(name = "transportation", schema = "cargo_delivery", catalog = "delivery")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Transportation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "cargo_id", nullable = false, length = -1)
    private String cargoId;
    @Basic
    @Column(name = "count", nullable = false)
    private long count;
    @Basic
    @Column(name = "transport_id", nullable = false, length = -1)
    private String transportId;
    @Basic
    @Column(name = "is_damage", nullable = false)
    private boolean isDamage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transportation that = (Transportation) o;
        return count == that.count && Objects.equals(id, that.id) && Objects.equals(cargoId, that.cargoId) && Objects.equals(transportId, that.transportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cargoId, count, transportId);
    }
}
