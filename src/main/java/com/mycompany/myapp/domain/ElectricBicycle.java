package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ElectricBicycle.
 */
@Entity
@Table(name = "electric_bicycle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ElectricBicycle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bicycle_id")
    private String bicycleID;

    @Column(name = "bicycle_model")
    private String bicycleModel;

    /*
        描述是不是需要维护的车，是不是需要
        true
        false
     */
    @Column(name = "bicycle_info")
    private String bicycleInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBicycleID() {
        return bicycleID;
    }

    public ElectricBicycle bicycleID(String bicycleID) {
        this.bicycleID = bicycleID;
        return this;
    }

    public void setBicycleID(String bicycleID) {
        this.bicycleID = bicycleID;
    }

    public String getBicycleModel() {
        return bicycleModel;
    }

    public ElectricBicycle bicycleModel(String bicycleModel) {
        this.bicycleModel = bicycleModel;
        return this;
    }

    public void setBicycleModel(String bicycleModel) {
        this.bicycleModel = bicycleModel;
    }

    public String getBicycleInfo() {
        return bicycleInfo;
    }

    public ElectricBicycle bicycleInfo(String bicycleInfo) {
        this.bicycleInfo = bicycleInfo;
        return this;
    }

    public void setBicycleInfo(String bicycleInfo) {
        this.bicycleInfo = bicycleInfo;
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
        ElectricBicycle electricBicycle = (ElectricBicycle) o;
        if (electricBicycle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), electricBicycle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ElectricBicycle{" +
            "id=" + getId() +
            ", bicycleID='" + getBicycleID() + "'" +
            ", bicycleModel='" + getBicycleModel() + "'" +
            ", bicycleInfo='" + getBicycleInfo() + "'" +
            "}";
    }
}
