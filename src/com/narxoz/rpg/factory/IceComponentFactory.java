package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.FrostBreath;
import com.narxoz.rpg.combat.IceShield;
import com.narxoz.rpg.loot.IceLootTable;
import com.narxoz.rpg.loot.LootTable;

import java.util.Arrays;
import java.util.List;

public class IceComponentFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(
                new IceShield(),
                new FrostBreath()
        );
    }

    @Override
    public LootTable createLootTable() {
        return new IceLootTable();
    }

    @Override
    public String createAIBehavior() {
        return "DEFENSIVE";
    }
}
