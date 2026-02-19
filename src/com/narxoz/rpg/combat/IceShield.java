package com.narxoz.rpg.combat;

public class IceShield implements Ability {

    private final String name;
    private final int damage;
    private final String description;

    private final int defenseBoost;
    private final int durationTurns;

    public IceShield() {
        this.name = "Ice Shield";
        this.damage = 0;
        this.defenseBoost = 40;
        this.durationTurns = 2;
        this.description = "Forms a barrier of ice that reduces incoming damage.";
    }

    private IceShield(IceShield other) {
        this.name = other.name;
        this.damage = other.damage;
        this.defenseBoost = other.defenseBoost;
        this.durationTurns = other.durationTurns;
        this.description = other.description;
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
        return new IceShield(this);
    }

    @Override
    public String toString() {
        return name + " (+" + defenseBoost + " def, " + durationTurns + " turns)";
    }
}
