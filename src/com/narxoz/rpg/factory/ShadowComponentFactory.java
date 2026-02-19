package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.BattleCry;
import com.narxoz.rpg.combat.ShadowStrike;
import com.narxoz.rpg.combat.Vanish;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.ShadowLootTable;

import java.util.Arrays;
import java.util.List;

public class ShadowComponentFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return Arrays.asList(
                new BattleCry(),
                new Vanish(),
                new ShadowStrike()
        );
    }

    @Override
    public LootTable createLootTable() {
        return new ShadowLootTable();
    }

    @Override
    public String createAIBehavior() {
        return "TACTICAL";
    }
}

