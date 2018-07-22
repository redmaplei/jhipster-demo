package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Round.
 */
@Entity
@Table(name = "round")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Round implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "radius")
    private Double radius;

    @Column(name = "perimeter")
    private Double perimeter;

    @Column(name = "pi")
    private Double pi;

    public Round() {};

    public Round(Double radius,Double perimeter,Double pi) {
        this.radius = radius;
        this.perimeter = perimeter;
        this.pi = pi;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRadius() {
        return radius;
    }

    public Round radius(Double radius) {
        this.radius = radius;
        return this;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public Round perimeter(Double perimeter) {
        this.perimeter = perimeter;
        return this;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Double getPi() {
        return pi;
    }

    public Round pi(Double pi) {
        this.pi = pi;
        return this;
    }

    public void setPi(Double pi) {
        this.pi = pi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Round round = (Round) o;
        if (round.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), round.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Round{" +
            "id=" + getId() +
            ", radius=" + getRadius() +
            ", perimeter=" + getPerimeter() +
            ", pi=" + getPi() +
            "}";
    }
}
