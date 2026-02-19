package com.narxoz.rpg.combat;

public class MeteorStorm implements Ability {

    private final String name;
    private final int damage;
    private final String description;

    private final int meteorCount;
    private final int cooldownTurns;

    public MeteorStorm() {
        this.name = "Meteor Storm";
        this.damage = 250;
        this.meteorCount = 5;
        this.cooldownTurns = 6;
        this.description =
                "Calls down a devastating storm of meteors from the heavens.";
    }

    private MeteorStorm(MeteorStorm other) {
        this.name = other.name;
        this.damage = other.damage;
        this.meteorCount = other.meteorCount;
        this.cooldownTurns = other.cooldownTurns;
        this.description = other.description;
    }

    public int getMeteorCount() {
        return meteorCount;
    }

    public int getCooldownTurns() {
        return cooldownTurns;
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
        return description +
                " (" + meteorCount + " meteors, cooldown: " +
                cooldownTurns + " turns)";
    }

    @Override
    public Ability clone() {
        return new MeteorStorm(this);
    }

    @Override
    public String toString() {
        return name +
                " [" + meteorCount + " meteors, " +
                damage + " dmg each, CD " +
                cooldownTurns + "]";
    }
}
