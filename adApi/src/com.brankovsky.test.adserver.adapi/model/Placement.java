package com.brankovsky.test.adserver.adapi.model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Алексей on 04.06.2017.
 */
@Entity
@Table(name = "placements", schema = "public", catalog = "adserverdb", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Placement {
    private int id;
    private String name;
    private List<Campaign> campaigns;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "placementstocampaigns", catalog = "adserverdb",
            joinColumns = {@JoinColumn(name = "placement_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "campaign_id", referencedColumnName = "id", nullable = false, updatable = false)})
    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Placement that = (Placement) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
