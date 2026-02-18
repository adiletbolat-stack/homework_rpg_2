package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.enemy.Enemy;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Example complex boss enemy — THE REASON BUILDER PATTERN EXISTS.
 *
 * ============================================================
 * READ THIS CAREFULLY — THIS IS THE CORE LEARNING MOMENT!
 * ============================================================
 *
 * Look at this constructor. REALLY look at it.
 * Count the parameters. Imagine using it in Main.java.
 * Imagine a teammate trying to understand what each parameter means.
 *
 * This is called the "Telescoping Constructor" anti-pattern.
 * It's the #1 problem that the Builder pattern solves.
 *
 * YOUR MISSION:
 * After studying this horror, you will create an EnemyBuilder
 * that constructs DragonBoss (and other complex enemies)
 * step-by-step, with clear and readable code.
 *
 * Compare:
 *
 * BEFORE (Telescoping Constructor — current code):
 * new DragonBoss("Fire Dragon", 50000, 500, 200, 50, "FIRE",
 * abilities, 30000, 15000, 5000, loot, "AGGRESSIVE",
 * true, true, 20);
 * // What does 'true, true, 20' mean? Nobody knows!
 *
 * AFTER (Builder Pattern — your implementation):
 * new BossEnemyBuilder()
 * .setName("Fire Dragon")
 * .setHealth(50000)
 * .setDamage(500)
 * .setDefense(200)
 * .setSpeed(50)
 * .setElement("FIRE")
 * .addAbility(new FlameBreath())
 * .addAbility(new WingBuffet())
 * .addPhase(1, 50000)
 * .addPhase(2, 30000)
 * .addPhase(3, 15000)
 * .setLootTable(fireLoot)
 * .setAI("AGGRESSIVE")
 * .build();
 * // Every parameter is labeled! Readable! Maintainable!
 *
 * ============================================================
 * TODO: After implementing your Builder, REFACTOR this class!
 * ============================================================
 * - Remove (or simplify) the telescoping constructor
 * - Make DragonBoss constructable ONLY through a Builder
 * - Make the built DragonBoss IMMUTABLE (no setters!)
 * - The Builder handles all the complexity
 */

public class DragonBoss implements Enemy {

    // --- Basic Stats ---
    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    // --- Elemental Theme ---
    private String element;

    // --- Abilities ---
    private List<Ability> abilities;

    // --- Boss Phases (health thresholds that trigger behavior changes) ---
    // Phase number -> health threshold at which this phase activates
    private Map<Integer, Integer> phases;

    // --- Loot ---
    private LootTable lootTable;

    // --- AI Behavior ---
    private String aiBehavior;

    // --- Special Properties ---
    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;

    /**
     * THE TELESCOPING CONSTRUCTOR FROM HELL.
     *
     * Count the parameters: FIFTEEN (15).
     * Can you tell which parameter is which when calling this?
     * Can you remember the order?
     * What if you want to add a 16th parameter later?
     *
     * THIS is why the Builder pattern exists.
     *
     * After you implement your Builder, this constructor should be
     * either simplified (package-private, called only by Builder)
     * or replaced entirely.
     */
    private DragonBoss(Builder b) {
        this.name = b.name;
        this.health = b.health;
        this.damage = b.damage;
        this.defense = b.defense;
        this.speed = b.speed;
        this.element = b.element;

        // Defensive copies + immutability wrappers
        this.abilities = Collections.unmodifiableList(new ArrayList<>(b.abilities));
        this.phases = Collections.unmodifiableMap(new LinkedHashMap<>(b.phases));

        this.lootTable = b.lootTable;
        this.aiBehavior = b.aiBehavior;

        this.canFly = b.canFly;
        this.hasBreathAttack = b.hasBreathAttack;
        this.wingspan = b.wingspan;
    }

    public static Builder builder() {
        return new Builder();
    }

    // TODO: Implement methods from Enemy interface

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public String getElement() {
        return element;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public Map<Integer, Integer> getPhases() {
        return phases;
    }

    public LootTable getLootTable() {
        return lootTable;
    }

    public String getAiBehavior() {
        return aiBehavior;
    }

    public boolean canFly() {
        return canFly;
    }

    public boolean hasBreathAttack() {
        return hasBreathAttack;
    }

    public int getWingspan() {
        return wingspan;
    }

    public void displayInfo() {
        System.out.println("=== " + name + " (Dragon Boss) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element);

        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.println("  - " + a);
        }

        System.out.println("Boss Phases: " + phases.size());
        for (Map.Entry<Integer, Integer> phase : phases.entrySet()) {
            System.out.println("  Phase " + phase.getKey() + ": triggers at " + phase.getValue() + " HP");
        }

        System.out.println("AI Behavior: " + aiBehavior);
        System.out.println("Can Fly: " + canFly
                + " | Breath Attack: " + hasBreathAttack
                + " | Wingspan: " + wingspan);

        System.out.println("Loot: " + (lootTable != null ? lootTable : "None"));
    }

    public static class Builder {

        private String name;
        private int health;
        private int damage;
        private int defense;
        private int speed;
        private String element = "NONE";

        private java.util.List<com.narxoz.rpg.combat.Ability> abilities = new java.util.ArrayList<>();
        private java.util.Map<Integer, Integer> phases = new java.util.LinkedHashMap<>();

        private com.narxoz.rpg.loot.LootTable lootTable;
        private String aiBehavior = "NEUTRAL";

        private boolean canFly;
        private boolean hasBreathAttack;
        private int wingspan;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setHealth(int health) {
            this.health = health;
            return this;
        }

        public Builder setDamage(int damage) {
            this.damage = damage;
            return this;
        }

        public Builder setDefense(int defense) {
            this.defense = defense;
            return this;
        }

        public Builder setSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public Builder setElement(String element) {
            this.element = element;
            return this;
        }

        public Builder addAbility(com.narxoz.rpg.combat.Ability ability) {
            if (ability != null)
                abilities.add(ability);
            return this;
        }

        public Builder addPhase(int phaseNumber, int threshold) {
            phases.put(phaseNumber, threshold);
            return this;
        }

        public Builder setLootTable(com.narxoz.rpg.loot.LootTable lootTable) {
            this.lootTable = lootTable;
            return this;
        }

        public Builder setAI(String aiBehavior) {
            this.aiBehavior = aiBehavior;
            return this;
        }

        public Builder setCanFly(boolean canFly) {
            this.canFly = canFly;
            return this;
        }

        public Builder setHasBreathAttack(boolean hasBreathAttack) {
            this.hasBreathAttack = hasBreathAttack;
            return this;
        }

        public Builder setWingspan(int wingspan) {
            this.wingspan = wingspan;
            return this;
        }

        public DragonBoss build() {

            if (name == null || name.trim().isEmpty()) {
                throw new IllegalStateException("Name required");
            }

            if (health <= 0) {
                throw new IllegalStateException("Health must be > 0");
            }

            if (phases.isEmpty()) {
                phases.put(1, health);
            }

            return new DragonBoss(this);
        }

    }

    @Override
    public Enemy clone() {
        DragonBoss.Builder b = DragonBoss.builder()
                .setName(this.name)
                .setHealth(this.health)
                .setDamage(this.damage)
                .setDefense(this.defense)
                .setSpeed(this.speed)
                .setElement(this.element)
                .setAI(this.aiBehavior)
                .setCanFly(this.canFly)
                .setHasBreathAttack(this.hasBreathAttack)
                .setWingspan(this.wingspan)
                .setLootTable(deepCopyLootTable(this.lootTable));

        for (Ability ability : this.abilities) {
            b.addAbility(deepCopyAbility(ability));
        }
        return b.build();
    }

    // Deep copy phases map (Integer is immutable so v

    // TODO: Implement clone() for Prototype pattern
    // DragonBoss has MANY fields that need deep copying:
    // - abilities (List<Ability>) → deep copy each ability
    // - phases (Map<Integer, Integer>) → copy the map
    // - lootTable → deep copy
    // - primitive fields → direct copy
    //
    // This is more complex than Goblin.clone()!
    // That's the challenge of Prototype with complex objects.

    // TODO: Add helper methods for variant creation
    // - void setElement(String element) — for elemental variants
    // - void multiplyStats(double multiplier) — for difficulty tiers

    private Ability deepCopyAbility(Ability ability) {
        if (ability == null)
            return null;
        return ability;
    }

    private LootTable deepCopyLootTable(LootTable lootTable) {
        if (lootTable == null)
            return null;
        return lootTable;
    }

}
