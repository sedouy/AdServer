package com.brankovsky.test.adserver.core.model;

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * Created by Алексей on 16.06.2017.
 */
public class Placement {
    private UUID id;
    private String name;
    protected List<? extends Campaign> campaigns;

    @NotNull
    public UUID getId() {
        return id;
    }

    public void setId(@NotNull UUID id) {
        assert id != null;

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<? extends Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<? extends Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Placement)) return false;

        Placement placement = (Placement) o;

        return id.equals(placement.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
