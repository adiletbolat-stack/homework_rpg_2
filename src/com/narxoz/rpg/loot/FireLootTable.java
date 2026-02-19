package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FireLootTable implements LootTable {

    private final List<String> items;
    private final int goldDrop;
    private final int experienceDrop;

    public FireLootTable() {
        this.items = Arrays.asList("Fire Gem", "Charred Scale", "Ember Core");
        this.goldDrop = 250;
        this.experienceDrop = 600;
    }

    private FireLootTable(FireLootTable other) {
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
        return "Fire Loot - Items: " + items + ", Gold: " + goldDrop + ", XP: " + experienceDrop;
    }

    @Override
    public LootTable clone() {
        return new FireLootTable(this);
    }

    @Override
    public String toString() {
        return getLootInfo();
    }
}
