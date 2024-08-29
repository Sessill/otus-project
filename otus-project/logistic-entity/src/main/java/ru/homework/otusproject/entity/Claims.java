package ru.homework.otusproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "claims", schema = "cargo_claims", catalog = "claim")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Claims {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = -1)
    private Long id;
    @Basic
    @Column(name = "cargo_id", nullable = false, length = -1)
    private String cargoId;
    @Basic
    @Column(name = "damage", nullable = false, length = -1)
    private String damage;
    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    private double amount;
    @Basic
    @Column(name = "status")
    private Long claim_status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claims claims = (Claims) o;
        return Double.compare(claims.amount, amount) == 0 && Objects.equals(id, claims.id) && Objects.equals(cargoId, claims.cargoId) && Objects.equals(damage, claims.damage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cargoId, damage, amount);
    }
}
