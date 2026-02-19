package com.narxoz.rpg.combat;

public class Vanish implements Ability {

    private final String name;
    private final int damage;
    private final String description;

    private final int evadePercent;
    private final int durationTurns;

    public Vanish() {
        this.name = "Vanish";
        this.damage = 0;
        this.evadePercent = 40;
        this.durationTurns = 2;
        this.description = "Disappears into shadows, increasing evasion temporarily.";
    }

    private Vanish(Vanish other) {
        this.name = other.name;
        this.damage = other.damage;
        this.description = other.description;
        this.evadePercent = other.evadePercent;
        this.durationTurns = other.durationTurns;
    }

    public int getEvadePercent() {
        return evadePercent;
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
        return description + " (Evasion: +" + evadePercent + "% for " + durationTurns + " turns)";
    }

    @Override
    public Ability clone() {
        return new Vanish(this);
    }

    @Override
    public String toString() {
        return name + " (+" + evadePercent + "% evasion, " + durationTurns + " turns)";
    }
}
