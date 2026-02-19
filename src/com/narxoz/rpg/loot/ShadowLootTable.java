package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShadowLootTable implements LootTable {

    private final List<String> items;
    private final int goldDrop;
    private final int experienceDrop;

    public ShadowLootTable() {
        this.items = new ArrayList<>(Arrays.asList("Shadow Essence", "Night Cloak Fragment", "Void Dust"));
        this.goldDrop = 300;
        this.experienceDrop = 700;
    }

    private ShadowLootTable(ShadowLootTable other) {
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
        return "Shadow Loot - Items: " + items + ", Gold: " + goldDrop + ", XP: " + experienceDrop;
    }

    @Override
    public LootTable clone() {
        return new ShadowLootTable(this);
    }

    @Override
    public String toString() {
        return getLootInfo();
    }
}
