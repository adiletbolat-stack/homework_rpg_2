package com.narxoz.rpg.combat;

public class FrostBreath implements Ability {

    private final String name;
    private final int damage;
    private final String description;

    private final int slowPercent;

    public FrostBreath() {
        this.name = "Frost Breath";
        this.damage = 90;
        this.slowPercent = 30;
        this.description = "Exhales freezing air that damages and slows targets.";
    }

    private FrostBreath(FrostBreath other) {
        this.name = other.name;
        this.damage = other.damage;
        this.description = other.description;
        this.slowPercent = other.slowPercent;
    }

    public int getSlowPercent() {
        return slowPercent;
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
        return description + " (Slow: " + slowPercent + "%)";
    }

    @Override
    public Ability clone() {
        return new FrostBreath(this);
    }

    @Override
    public String toString() {
        return name + " (" + damage + " dmg, " + slowPercent + "% slow)";
    }
}

