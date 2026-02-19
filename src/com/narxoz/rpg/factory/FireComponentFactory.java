package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.FireShield;
import com.narxoz.rpg.combat.FlameBreath;
import com.narxoz.rpg.combat.MeteorStorm;
import com.narxoz.rpg.loot.FireLootTable;
import com.narxoz.rpg.loot.LootTable;

import java.util.Arrays;
import java.util.List;

public class FireComponentFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(
                new FlameBreath(),
                new FireShield(),
                new MeteorStorm()
        );
    }

    @Override
    public LootTable createLootTable() {
        return new FireLootTable();
    }

    @Override
    public String createAIBehavior() {
        return "AGGRESSIVE";
    }
}

