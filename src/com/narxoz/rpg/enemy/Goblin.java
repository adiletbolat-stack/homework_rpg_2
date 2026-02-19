package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class Goblin implements Enemy {

    private String name;

    private int health;
    private int damage;
    private int defense;
    private int speed;

    private List<Ability> abilities;
    private LootTable lootTable;

    private String element = "NONE";
    private String aiBehavior = "NEUTRAL";

    public Goblin(String name) {
        this.name = name;

        this.health = 100;
        this.damage = 15;
        this.defense = 5;
        this.speed = 35;

        this.abilities = new ArrayList<>();
        this.lootTable = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public List<Ability> getAbilities() {
        return new ArrayList<>(abilities);
    }

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " (Goblin) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element + " | AI: " + aiBehavior);

        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.println("  - " + a.getName() + ": " + a.getDescription());
        }

        System.out.println("Loot: " + (lootTable != null ? lootTable.getLootInfo() : "None"));
    }

    @Override
    public Enemy clone() {
        Goblin copy = new Goblin(this.name);
        copy.health = this.health;
        copy.damage = this.damage;
        copy.defense = this.defense;
        copy.speed = this.speed;

        copy.element = this.element;
        copy.aiBehavior = this.aiBehavior;

        copy.abilities = new ArrayList<>();
        for (Ability a : this.abilities) {
            copy.abilities.add(a != null ? a.clone() : null);
        }

        copy.lootTable = (this.lootTable != null) ? this.lootTable.clone() : null;

        return copy;
    }

    public void multiplyStats(double multiplier) {
        if (multiplier <= 0) {
            throw new IllegalArgumentException("Multiplier must be > 0");
        }

        this.health = (int) Math.round(this.health * multiplier);
        this.damage = (int) Math.round(this.damage * multiplier);
        this.defense = (int) Math.round(this.defense * multiplier);
        this.speed = (int) Math.round(this.speed * multiplier);
    }

    public void addAbility(Ability ability) {
        if (ability != null) {
            abilities.add(ability);
        }
    }

    public void setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStats(int health, int damage, int defense, int speed) {
        if (health <= 0) throw new IllegalArgumentException("Health must be > 0");
        if (damage < 0 || defense < 0 || speed < 0) {
            throw new IllegalArgumentException("Stats cannot be negative");
        }

        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
    }

    public void setElement(String element) {
        this.element = (element == null || element.isEmpty()) ? "NONE" : element;
    }

    public void setAI(String aiBehavior) {
        this.aiBehavior = (aiBehavior == null || aiBehavior.isEmpty()) ? "NEUTRAL" : aiBehavior;
    }
}
