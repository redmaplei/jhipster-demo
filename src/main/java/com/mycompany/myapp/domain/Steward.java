package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Steward.
 */
@Entity
@Table(name = "steward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Steward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "steward_id")
    private String stewardID;

    @Column(name = "steward_name")
    private String stewardName;

    /*
        ture代表现在这名维护员可以维护电动自行车
        false相反
     */



    /**
     *
     */
    @Column(name = "steward_info")
    private String stewardInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getStewardID() {
        return stewardID;
    }

    /**
     *
     * @param stewardID
     * @return
     */
    public Steward stewardID(String stewardID) {
        this.stewardID = stewardID;
        return this;
    }

    public void setStewardID(String stewardID) {
        this.stewardID = stewardID;
    }

    public String getStewardName() {
        return stewardName;
    }

    public Steward stewardName(String stewardName) {
        this.stewardName = stewardName;
        return this;
    }

    public void setStewardName(String stewardName) {
        this.stewardName = stewardName;
    }

    public String getStewardInfo() {
        return stewardInfo;
    }

    public Steward stewardInfo(String stewardInfo) {
        this.stewardInfo = stewardInfo;
        return this;
    }

    public void setStewardInfo(String stewardInfo) {
        this.stewardInfo = stewardInfo;
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
        Steward steward = (Steward) o;
        if (steward.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), steward.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Steward{" +
            "id=" + getId() +
            ", stewardID='" + getStewardID() + "'" +
            ", stewardName='" + getStewardName() + "'" +
            ", stewardInfo='" + getStewardInfo() + "'" +
            "}";
    }
}
