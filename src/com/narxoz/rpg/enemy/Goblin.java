package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.enemy.Enemy;

import java.util.List;
import java.util.ArrayList;

/**
 * Example basic enemy implementation — a simple Goblin.
 *
 * This is provided as a REFERENCE to show enemy structure.
 * Study this implementation, then create more enemies.
 *
 * Notice:
 * - Simple stats (low health, low damage)
 * - Basic constructor (only a few parameters — no Builder needed!)
 * - This is intentionally simple to contrast with DragonBoss.java
 *
 * ============================================================
 * IMPORTANT OBSERVATION:
 * ============================================================
 *
 * A Goblin is simple: name, health, damage, defense — done.
 * A regular constructor works fine here:
 * new Goblin("Forest Goblin")
 *
 * But look at DragonBoss.java... THAT'S where Builder shines!
 * Simple objects don't need Builder. Complex objects do.
 * Knowing WHEN to use a pattern is as important as knowing HOW.
 *
 * ============================================================
 * PROTOTYPE PATTERN NOTE:
 * ============================================================
 *
 * Goblin is a GREAT candidate for Prototype pattern!
 * Imagine you need 50 goblins for a dungeon. Instead of:
 * new Goblin("Goblin 1"), new Goblin("Goblin 2"), ...
 *
 * You can:
 * Goblin template = new Goblin("Goblin");
 * Enemy goblin1 = template.clone(); // Fast!
 * Enemy goblin2 = template.clone(); // Fast!
 *
 * And for variants:
 * Enemy elite = template.clone();
 * // modify elite's stats to 2x
 *
 * TODO: Implement the clone() method with DEEP COPY.
 * TODO: Create similar basic enemies: Skeleton, Orc, etc.
 * TODO: Consider what needs deep vs shallow copy here.
 * - Primitive stats (health, damage) → shallow copy is fine
 * - Ability list → MUST be deep copied!
 * - LootTable → MUST be deep copied!
 */
public class Goblin implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private List<Ability> abilities;
    private LootTable lootTable;

    public Goblin(String name) {
        this.name = name;

        this.health = 100;
        this.damage = 15;
        this.defense = 5;
        this.speed = 35;

        this.abilities = new ArrayList<>();
        this.lootTable = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public List<Ability> getAbilities() {
        return new ArrayList<>(abilities);
    }

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " (Goblin) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);

        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.println("  - " + a);
        }

        System.out.println("Loot: " + (lootTable != null ? lootTable : "None"));
    }

    @Override
    public Enemy clone() {
        Goblin copy = new Goblin(this.name);

        copy.health = this.health;
        copy.damage = this.damage;
        copy.defense = this.defense;
        copy.speed = this.speed;

        copy.abilities = new ArrayList<>();
        for (Ability a : this.abilities) {
            copy.abilities.add(deepCopyAbility(a));
        }

        copy.lootTable = deepCopyLootTable(this.lootTable);

        return copy;
    }

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

    public void multiplyStats(double multiplier) {
        if (multiplier <= 0)
            throw new IllegalArgumentException("Multiplier must be > 0");

        this.health = (int) Math.round(this.health * multiplier);
        this.damage = (int) Math.round(this.damage * multiplier);
        this.defense = (int) Math.round(this.defense * multiplier);
        this.speed = (int) Math.round(this.speed * multiplier);
    }

    public void addAbility(Ability ability) {
        if (ability != null)
            abilities.add(ability);
    }

    public void setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
    }

    public void setName(String name) {
        this.name = name;
    }
}
