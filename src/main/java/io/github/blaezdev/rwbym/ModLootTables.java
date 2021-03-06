package io.github.blaezdev.rwbym;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

import static io.github.blaezdev.rwbym.RWBYModels.MODID;

public class ModLootTables {
    public static final ResourceLocation BEOWOLF = register("beowolf");
    public static final ResourceLocation Boarbatusk = register("boarbatusk");
    public static final ResourceLocation Ursa = register("ursa");
    public static final ResourceLocation Boss = register("boss");
    public static final ResourceLocation Demiboss = register("demiboss");
    public static final ResourceLocation Armagigas = register("armagigas");
    public static final ResourceLocation MutantDeath = register("mutantdeath");
    public static final ResourceLocation RaidBoss = register("raidboss");

    private static ResourceLocation register(String id) {
        return LootTableList.register(new ResourceLocation(MODID, id));
    }
}
