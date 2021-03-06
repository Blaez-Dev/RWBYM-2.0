package io.github.blaezdev.rwbym.Init;

import io.github.blaezdev.rwbym.utility.RWBYConfig;
import io.github.blaezdev.rwbym.world.biome.BiomeCliffsideForest;
import io.github.blaezdev.rwbym.world.biome.BiomeDarkGrimm;
import io.github.blaezdev.rwbym.world.biome.BiomeDomainofLight;
import io.github.blaezdev.rwbym.world.biome.BiomeForeverFall;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RWBYBiomes {

    public static final Biome FOREVER_FALL = new BiomeForeverFall();
    public static final Biome CLIFF_SIDE = new BiomeCliffsideForest();
    public static final Biome GrimmWastes = new BiomeDarkGrimm();
    public static final Biome DomainofLight = new BiomeDomainofLight();

    public static void registerBiomes() {
        initBiome(FOREVER_FALL, "forever_fall", BiomeType.WARM, Type.FOREST);
        initBiome(CLIFF_SIDE, "cliff_side", BiomeType.COOL, Type.FOREST);
        initBiome(GrimmWastes, "grimmwastes", BiomeType.DESERT, Type.DRY);
        initBiome(DomainofLight, "domainoflight", BiomeType.DESERT, Type.DRY);
    }

    private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        //System.out.println("Registered Biome: " + name);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, RWBYConfig.worldgen.biomerarity));
        //BiomeManager.addSpawnBiome(biome);
        return biome;
    }
}
