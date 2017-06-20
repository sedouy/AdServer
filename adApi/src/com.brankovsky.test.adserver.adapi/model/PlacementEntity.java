package com.brankovsky.test.adserver.adapi.model;

import com.brankovsky.test.adserver.core.model.Placement;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by Алексей on 04.06.2017.
 */
@Entity
@Table(name = "placements", schema = "public", catalog = "adserverdb", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class PlacementEntity extends Placement {
    public PlacementEntity() {

    }

    public PlacementEntity(Placement placement) {
        setId(placement.getId());
        setName(placement.getName());
    }

    @Id
    @Column(name = "id", nullable = false)
    public UUID getId() {
        return super.getId();
    }

    public void setId(UUID id) {
        super.setId(id);
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "placementstocampaigns", catalog = "adserverdb",
            joinColumns = {@JoinColumn(name = "placement_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "campaign_id", referencedColumnName = "id", nullable = false, updatable = false)})
    public List<CampaignEntity> getCampaignsEntities() {
        return (List<CampaignEntity>)super.getCampaigns();
    }

    public void setCampaignsEntities(List<CampaignEntity> campaigns) {
        this.campaigns = campaigns;
    }
}
