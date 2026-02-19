package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IceLootTable implements LootTable {

    private final List<String> items;
    private final int goldDrop;
    private final int experienceDrop;

    public IceLootTable() {
        this.items = new ArrayList<>(Arrays.asList("Ice Crystal", "Frozen Fang", "Glacier Shard"));
        this.goldDrop = 220;
        this.experienceDrop = 600;
    }

    private IceLootTable(IceLootTable other) {
        this.items = new ArrayList<>(other.items);
        this.goldDrop = other.goldDrop;
        this.experienceDrop = other.experienceDrop;
    }

    @Override
    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public int getGoldDrop() {
        return goldDrop;
    }

    @Override
    public int getExperienceDrop() {
        return experienceDrop;
    }

    @Override
    public String getLootInfo() {
        return "Ice Loot - Items: " + items + ", Gold: " + goldDrop + ", XP: " + experienceDrop;
    }

    @Override
    public LootTable clone() {
        return new IceLootTable(this);
    }

    @Override
    public String toString() {
        return getLootInfo();
    }
}

