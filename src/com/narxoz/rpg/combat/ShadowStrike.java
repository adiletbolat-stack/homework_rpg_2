package com.narxoz.rpg.combat;

public class ShadowStrike implements Ability {

    private final String name;
    private final int damage;
    private final String description;

    private final int critChancePercent;

    public ShadowStrike() {
        this.name = "Shadow Strike";
        this.damage = 110;
        this.critChancePercent = 25;
        this.description = "Strikes from the darkness with deadly precision.";
    }

    private ShadowStrike(ShadowStrike other) {
        this.name = other.name;
        this.damage = other.damage;
        this.description = other.description;
        this.critChancePercent = other.critChancePercent;
    }

    public int getCritChancePercent() {
        return critChancePercent;
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
        return description + " (Crit chance: " + critChancePercent + "%)";
    }

    @Override
    public Ability clone() {
        return new ShadowStrike(this);
    }

    @Override
    public String toString() {
        return name + " (" + damage + " dmg, " + critChancePercent + "% crit)";
    }
}
