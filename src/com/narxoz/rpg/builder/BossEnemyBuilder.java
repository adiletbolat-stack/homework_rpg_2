package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.DragonBoss;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BossEnemyBuilder implements EnemyBuilder {

    private String name;
    private int health;

    private int damage = 0;
    private int defense = 0;
    private int speed = 0;

    private String element = "NONE";
    private final List<Ability> abilities = new ArrayList<>();
    private final Map<Integer, Integer> phases = new LinkedHashMap<>();

    private LootTable lootTable;
    private String aiBehavior = "NEUTRAL";

    private boolean canFly = false;
    private boolean hasBreathAttack = false;
    private int wingspan = 0;

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
        this.element = element;
        return this;
    }

    @Override
    public EnemyBuilder addAbility(Ability ability) {
        if (ability != null)
            abilities.add(ability);
        return this;
    }

    @Override
    public EnemyBuilder addAbilities(List<Ability> abilities) {
        if (abilities != null) {
            for (Ability ability : abilities) {
                addAbility(ability);
            }
        }
        return this;
    }

    @Override
    public EnemyBuilder addPhase(int phaseNumber, int healthThreshold) {
        phases.put(phaseNumber, healthThreshold);
        return this;
    }

    @Override
    public EnemyBuilder setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
        return this;
    }

    @Override
    public EnemyBuilder setAI(String aiBehavior) {
        this.aiBehavior = aiBehavior;
        return this;
    }

    @Override
    public EnemyBuilder setCanFly(boolean canFly) {
        this.canFly = canFly;
        return this;
    }

    @Override
    public EnemyBuilder setHasBreathAttack(boolean hasBreathAttack) {
        this.hasBreathAttack = hasBreathAttack;
        return this;
    }

    @Override
    public EnemyBuilder setWingspan(int wingspan) {
        this.wingspan = wingspan;
        return this;
    }

    @Override
    public Enemy build() {
        validate();

        if (phases.isEmpty()) {
            phases.put(1, health);
        }

        DragonBoss.Builder b = DragonBoss.builder()
                .setName(name)
                .setHealth(health)
                .setDamage(damage)
                .setDefense(defense)
                .setSpeed(speed)
                .setElement(element)
                .setLootTable(lootTable)
                .setAI(aiBehavior)
                .setCanFly(canFly)
                .setHasBreathAttack(hasBreathAttack)
                .setWingspan(wingspan);

        for (Ability a : abilities) {
            b.addAbility(a);
        }

        for (Map.Entry<Integer, Integer> e : phases.entrySet()) {
            b.addPhase(e.getKey(), e.getValue());
        }

        return b.build();
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
        if (canFly && wingspan <= 0) {
            throw new IllegalStateException("Wingspan must be > 0 if canFly is true");
        }
    }
}
