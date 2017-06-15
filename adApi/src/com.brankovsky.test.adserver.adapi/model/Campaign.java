package com.brankovsky.test.adserver.adapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 04.06.2017.
 */
@Entity
@Table(name = "campaigns", schema = "public", catalog = "adserverdb", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Campaign {
    private int id;
    private String name;
    private short weight;
    private String slogan;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    public short getWeight() {
        return weight;
    }

    public void setWeight(short weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "slogan", nullable = false, length = -1)
    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campaign that = (Campaign) o;

        if (id != that.id) return false;
        if (weight != that.weight) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (slogan != null ? !slogan.equals(that.slogan) : that.slogan != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) weight;
        result = 31 * result + (slogan != null ? slogan.hashCode() : 0);
        return result;
    }
}
