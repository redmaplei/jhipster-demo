package com.mycompany.myapp.domain;

import javax.persistence.*;

/**
 * Create by wys on 2018/7/22
 */
@Entity
@Table(name = "RoundC")
public class RoundC {

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

    @Column(name = "n")
    private Double n;

    public RoundC() {};

    public RoundC(Double radius,Double perimeter,Double pi,Double n) {
        this.radius = radius;
        this.perimeter = perimeter;
        this.pi = pi;
        this.n = n;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Double getPi() {
        return pi;
    }

    public void setPi(Double pi) {
        this.pi = pi;
    }

    public Double getN() {
        return n;
    }

    public void setN(Double n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "RoundC{" +
            "id=" + id +
            ", radius=" + radius +
            ", perimeter=" + perimeter +
            ", pi=" + pi +
            ", n=" + n +
            '}';
    }
}
