package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class BasicEnemyBuilder implements EnemyBuilder {

    private String name;
    private int health;

    private int damage = 0;
    private int defense = 0;
    private int speed = 0;

    private String element = "NONE";
    private final List<Ability> abilities = new ArrayList<>();

    private LootTable lootTable;
    private String aiBehavior = "NEUTRAL";

    @Override
    public EnemyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public EnemyBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    @Override
    public EnemyBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public EnemyBuilder setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    @Override
    public EnemyBuilder setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public EnemyBuilder setElement(String element) {
        this.element = (element == null || element.trim().isEmpty()) ? "NONE" : element;
        return this;
    }

    @Override
    public EnemyBuilder addAbility(Ability ability) {
        if (ability != null) {
            abilities.add(ability.clone());
        }
        return this;
    }

    @Override
    public EnemyBuilder addAbilities(List<Ability> abilities) {
        if (abilities != null) {
            for (Ability a : abilities) {
                addAbility(a);
            }
        }
        return this;
    }

    @Override
    public EnemyBuilder addPhase(int phaseNumber, int healthThreshold) {
        throw new UnsupportedOperationException("Basic enemies do not support boss phases.");
    }

    @Override
    public EnemyBuilder setLootTable(LootTable lootTable) {
        this.lootTable = (lootTable != null) ? lootTable.clone() : null;
        return this;
    }

    @Override
    public EnemyBuilder setAI(String aiBehavior) {
        this.aiBehavior = (aiBehavior == null || aiBehavior.trim().isEmpty()) ? "NEUTRAL" : aiBehavior;
        return this;
    }

    @Override
    public EnemyBuilder setCanFly(boolean canFly) {
        throw new UnsupportedOperationException("Basic enemies do not support flying.");
    }

    @Override
    public EnemyBuilder setHasBreathAttack(boolean hasBreathAttack) {
        throw new UnsupportedOperationException("Basic enemies do not support breath attacks.");
    }

    @Override
    public EnemyBuilder setWingspan(int wingspan) {
        throw new UnsupportedOperationException("Basic enemies do not support wingspan.");
    }

    @Override
    public Enemy build() {
        validate();

        Goblin g = new Goblin(name);

        g.setStats(health, damage, defense, speed);
        g.setElement(element);
        g.setAI(aiBehavior);

        for (Ability a : abilities) {
            g.addAbility(a); 
        }

        g.setLootTable(lootTable);

        Enemy result = g;
        reset();
        return result;
    }

    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Enemy must have a name");
        }
        if (health <= 0) {
            throw new IllegalStateException("Enemy must have health > 0");
        }
        if (damage < 0 || defense < 0 || speed < 0) {
            throw new IllegalStateException("Stats cannot be negative");
        }
    }

    private void reset() {
        name = null;
        health = 0;

        damage = 0;
        defense = 0;
        speed = 0;

        element = "NONE";
        abilities.clear();

        lootTable = null;
        aiBehavior = "NEUTRAL";
    }
}
