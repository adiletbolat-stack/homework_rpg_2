package com.narxoz.rpg.combat;

public class BattleCry implements Ability {

    private final String name;
    private final int damage;
    private final String description;

    private final int attackBoostPercent;
    private final int durationTurns;

    public BattleCry() {
        this.name = "Battle Cry";
        this.damage = 0;
        this.attackBoostPercent = 25;
        this.durationTurns = 3;
        this.description = "A fearsome shout that boosts allies' attack for a short time.";
    }

    private BattleCry(BattleCry other) {
        this.name = other.name;
        this.damage = other.damage;
        this.attackBoostPercent = other.attackBoostPercent;
        this.durationTurns = other.durationTurns;
        this.description = other.description;
    }

    public int getAttackBoostPercent() {
        return attackBoostPercent;
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
        return description + " (+" + attackBoostPercent + "% for " + durationTurns + " turns)";
    }

    @Override
    public Ability clone() {
        return new BattleCry(this);
    }

    @Override
    public String toString() {
        return name + " (+" + attackBoostPercent + "%, " + durationTurns + " turns)";
    }
}

