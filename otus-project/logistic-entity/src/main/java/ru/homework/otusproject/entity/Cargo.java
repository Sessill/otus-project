package ru.homework.otusproject.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cargo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = -1)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "count", nullable = false)
    private long count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return count == cargo.count && Objects.equals(id, cargo.id) && Objects.equals(name, cargo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, count);
    }
}
