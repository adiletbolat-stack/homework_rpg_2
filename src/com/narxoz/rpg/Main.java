package com.narxoz.rpg;

import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;

import java.util.List;

/**
 * Main demonstration class for the RPG Enemy System.
 *
 * ============================================================
 * CREATIONAL PATTERNS CAPSTONE
 * ============================================================
 *
 * This demo showcases ALL FOUR creational design patterns:
 *   1. ABSTRACT FACTORY — Create themed enemy component families
 *   2. BUILDER          — Construct complex enemies step-by-step
 *   3. FACTORY METHOD   — Embedded in Builder.build()
 *   4. PROTOTYPE        — Clone enemies into variants efficiently
 *
 * Pipeline:
 *   Abstract Factory -> Builder -> (Factory Method: build()) -> Prototype
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Enemy System - Creational Patterns Capstone ===\n");

        // ============================================================
        // PART 1: ABSTRACT FACTORY PATTERN
        // ============================================================
        System.out.println("============================================");
        System.out.println("PART 1: ABSTRACT FACTORY - Themed Components");
        System.out.println("============================================\n");

        EnemyComponentFactory fireFactory = new FireComponentFactory();
        EnemyComponentFactory iceFactory = new IceComponentFactory();
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();

        createThemeDemo("FIRE", fireFactory);
        createThemeDemo("ICE", iceFactory);
        createThemeDemo("SHADOW", shadowFactory);

        System.out.println("Note: Because each theme has ONE factory, components stay consistent.");
        System.out.println("You do not manually mix abilities/loot from different themes.\n");

        // ============================================================
        // PART 2: BUILDER PATTERN (+ Factory Method inside build())
        // ============================================================
        System.out.println("============================================");
        System.out.println("PART 2: BUILDER - Complex Enemy Construction");
        System.out.println("============================================\n");

        Enemy fireDragon = new BossEnemyBuilder()
                .setName("Ancient Fire Dragon")
                .setHealth(50000)
                .setDamage(500)
                .setDefense(200)
                .setSpeed(50)
                .setElement("FIRE")
                .addAbilities(fireFactory.createAbilities())
                .setLootTable(fireFactory.createLootTable())
                .setAI(fireFactory.createAIBehavior())
                .setCanFly(true)
                .setHasBreathAttack(true)
                .setWingspan(20)
                .addPhase(1, 50000)
                .addPhase(2, 30000)
                .addPhase(3, 15000)
                .build(); 

        System.out.println("[Built Boss Enemy]");
        displayEnemyDetails(fireDragon);

        Enemy caveGoblin = new BasicEnemyBuilder()
                .setName("Cave Goblin")
                .setHealth(120)
                .setDamage(18)
                .setDefense(6)
                .setSpeed(40)
                .setElement("EARTH")
                .setAI("COWARDLY")
                .build(); 

        System.out.println("[Built Basic Enemy]");
        displayEnemyDetails(caveGoblin);

        // ============================================================
        // PART 3: PROTOTYPE PATTERN
        // ============================================================
        System.out.println("============================================");
        System.out.println("PART 3: PROTOTYPE - Enemy Cloning & Variants");
        System.out.println("============================================\n");

        EnemyRegistry registry = new EnemyRegistry();
        registry.registerTemplate("goblin", caveGoblin);
        registry.registerTemplate("fire-dragon", fireDragon);

        System.out.println("Registered templates: " + registry.listTemplates());
        System.out.println();

        Goblin eliteGoblin = (Goblin) registry.createFromTemplate("goblin");
        eliteGoblin.setName("Elite Goblin");
        eliteGoblin.multiplyStats(2.0);
        eliteGoblin.addAbility(shadowFactory.createAbilities().get(0).clone());
        eliteGoblin.setLootTable(shadowFactory.createLootTable().clone());

        System.out.println("[Variant: Elite Goblin (2x stats + Shadow ability/loot)]");
        displayEnemyDetails(eliteGoblin);

        demonstrateDeepCopy(caveGoblin, eliteGoblin);

        // ============================================================
        // PART 4: ALL PATTERNS WORKING TOGETHER
        // ============================================================
        System.out.println("============================================");
        System.out.println("PART 4: ALL PATTERNS WORKING TOGETHER");
        System.out.println("============================================\n");

        Enemy demonLord = new BossEnemyBuilder()
                .setName("Demon Lord")
                .setHealth(65000)
                .setDamage(650)
                .setDefense(300)
                .setSpeed(55)
                .setElement("SHADOW")
                .addAbilities(shadowFactory.createAbilities())
                .setLootTable(shadowFactory.createLootTable())
                .setAI(shadowFactory.createAIBehavior())
                .setCanFly(false)
                .setHasBreathAttack(false)
                .addPhase(1, 65000)
                .addPhase(2, 40000)
                .addPhase(3, 20000)
                .build();

        System.out.println("[Built Template Boss: Demon Lord]");
        displayEnemyDetails(demonLord);


        registry.registerTemplate("demon-lord", demonLord);


        Enemy greaterDemon = registry.createFromTemplate("demon-lord");

        System.out.println("[Cloned Variant: Greater Demon (clone of Demon Lord template)]");
        displayEnemyDetails(greaterDemon);

        // ============================================================
        // SUMMARY
        // ============================================================
        System.out.println("============================================");
        System.out.println("PATTERN SUMMARY");
        System.out.println("============================================");
        System.out.println("Abstract Factory: Themed component families (Fire, Ice, Shadow)");
        System.out.println("Builder: Step-by-step enemy construction (fluent interface)");
        System.out.println("Factory Method: Builder.build() creates the Enemy products");
        System.out.println("Prototype: EnemyRegistry clones templates into variants (deep copy)");

        System.out.println("\n=== Demo Complete ===");
    }

    // ------------------------------------------------------------
    // Helper methods (clean output + deep copy proof)
    // ------------------------------------------------------------

    private static void createThemeDemo(String themeName, EnemyComponentFactory factory) {
        System.out.println("[" + themeName + " FACTORY]");
        List<Ability> abilities = factory.createAbilities();
        System.out.println("AI Behavior: " + factory.createAIBehavior());
        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.println("  - " + a.getName() + " | dmg=" + a.getDamage());
        }
        System.out.println("Loot: " + factory.createLootTable().getLootInfo());
        System.out.println();
    }

    private static void displayEnemyDetails(Enemy enemy) {
        enemy.displayInfo();
        System.out.println();
    }

    private static void demonstrateDeepCopy(Enemy originalTemplate, Enemy cloneVariant) {
        System.out.println("---- Deep Copy Check ----");
        System.out.println("Template abilities count: " + originalTemplate.getAbilities().size());
        System.out.println("Clone abilities count:    " + cloneVariant.getAbilities().size());
        System.out.println("If clone changes do NOT affect template, Prototype deep copy works.\n");
    }
}
