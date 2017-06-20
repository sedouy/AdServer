package com.brankovsky.test.adserver.adapi.model;

import com.brankovsky.test.adserver.core.model.Campaign;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Алексей on 04.06.2017.
 */
@Entity
@Table(name = "campaigns", schema = "public", catalog = "adserverdb", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CampaignEntity extends Campaign {

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

    @Basic
    @Column(name = "weight", nullable = false)
    public short getWeight() {
        return super.getWeight();
    }

    public void setWeight(short weight) {
        super.setWeight(weight);
    }

    @Basic
    @Column(name = "slogan", nullable = false, length = -1)
    public String getSlogan() {
        return super.getSlogan();
    }

    public void setSlogan(String slogan) {
        super.setSlogan(slogan);
    }
}
