package com.brankovsky.test.adserver.core.model;

import com.sun.istack.internal.NotNull;

import java.util.UUID;

/**
 * Created by Алексей on 16.06.2017.
 */
public class Campaign {
    private UUID id;
    private String name;
    private short weight;
    private String slogan;

    @NotNull
    public UUID getId() {
        return id;
    }

    @NotNull
    public void setId(UUID id) {
        assert id != null;

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getWeight() {
        return weight;
    }

    public void setWeight(short weight) {
        this.weight = weight;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign)) return false;

        Campaign campaign = (Campaign) o;

        return id.equals(campaign.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
