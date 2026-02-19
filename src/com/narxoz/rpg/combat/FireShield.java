package com.narxoz.rpg.combat;

public class FireShield implements Ability {

    private final String name;
    private final int damage; 
    private final String description;

    private final int defenseBoost;
    private final int durationTurns;

    public FireShield() {
        this.name = "Fire Shield";
        this.damage = 0;
        this.defenseBoost = 35;
        this.durationTurns = 3;
        this.description = "Surrounds the enemy in flames, reducing incoming damage.";
    }

    private FireShield(FireShield other) {
        this.name = other.name;
        this.damage = other.damage;
        this.description = other.description;
        this.defenseBoost = other.defenseBoost;
        this.durationTurns = other.durationTurns;
    }

    public int getDefenseBoost() {
        return defenseBoost;
    }

    public int getDurationTurns() {
        return durationTurns;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getDescription() {
        return description + " (+" + defenseBoost + " defense for " + durationTurns + " turns)";
    }

    @Override
    public Ability clone() {
        return new FireShield(this);
    }

    @Override
    public String toString() {
        return name + " (+" + defenseBoost + " def, " + durationTurns + " turns)";
    }
}
