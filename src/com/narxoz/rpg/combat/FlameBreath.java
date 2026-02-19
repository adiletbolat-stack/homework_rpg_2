package com.narxoz.rpg.combat;

public class FlameBreath implements Ability {

    private final String name;
    private final int damage;
    private final String description;

    public FlameBreath() {
        this.name = "Flame Breath";
        this.damage = 120;
        this.description = "Unleashes a cone of scorching fire, burning everything in front.";
    }

    private FlameBreath(FlameBreath other) {
        this.name = other.name;
        this.damage = other.damage;
        this.description = other.description;
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
        return description;
    }

    @Override
    public Ability clone() {
        return new FlameBreath(this);
    }

    @Override
    public String toString() {
        return name + " (" + damage + " dmg)";
    }
}
