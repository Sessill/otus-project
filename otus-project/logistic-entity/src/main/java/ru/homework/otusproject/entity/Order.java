package ru.homework.otusproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "cargo_orders", catalog = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = -1)
    private Long id;
    @Basic
    @Column(name = "cargo_id", nullable = false, length = -1)
    private String cargoId;
    @Basic
    @Column(name = "load_place", nullable = false, length = -1)
    private String loadPlace;
    @Basic
    @Column(name = "unload_place", nullable = false, length = -1)
    private String unloadPlace;
    @Basic
    @Column(name = "count", nullable = false)
    private long count;
    @Basic
    @Column(name = "order_data", nullable = false)
    private Timestamp orderData;
    @Basic
    @Column(name = "plan_data", nullable = false)
    private Timestamp planData;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return count == order.count && Objects.equals(id, order.id) && Objects.equals(cargoId, order.cargoId) && Objects.equals(loadPlace, order.loadPlace) && Objects.equals(unloadPlace, order.unloadPlace) && Objects.equals(orderData, order.orderData) && Objects.equals(planData, order.planData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cargoId, loadPlace, unloadPlace, count, orderData, planData);
    }
}
