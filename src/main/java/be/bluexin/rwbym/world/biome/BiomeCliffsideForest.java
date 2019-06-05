package be.bluexin.rwbym.world.biome;

import be.bluexin.rwbym.entity.EntityApathy;
import be.bluexin.rwbym.entity.EntityBeowolf;
import be.bluexin.rwbym.entity.EntityBoarbatusk;
import be.bluexin.rwbym.entity.EntityUrsa;
import be.bluexin.rwbym.utility.RWBYConfig;
import net.minecraft.world.biome.Biome;

public class BiomeCliffsideForest extends Biome{
        public BiomeCliffsideForest() {
            super(new BiomeProperties("CliffSideForest").setBaseBiome("forest").setRainfall(1F).setHeightVariation(0.1F).setTemperature(0F));
            this.spawnableCaveCreatureList.clear();
            this.spawnableCreatureList.clear();
            this.spawnableMonsterList.clear();
            this.spawnableWaterCreatureList.clear();
            this.decorator.treesPerChunk = 7;
            this.spawnableCreatureList.add(new SpawnListEntry(EntityBeowolf.class, RWBYConfig.spawnratebeowolf*8, 1, 16));
            this.spawnableCreatureList.add(new SpawnListEntry(EntityBoarbatusk.class, RWBYConfig.spawnrateboar, 0, 3));
            this.spawnableCreatureList.add(new SpawnListEntry(EntityUrsa.class, RWBYConfig.spawnrateursa, 0, 3));
            this.spawnableCreatureList.add(new SpawnListEntry(EntityApathy.class, RWBYConfig.spawnrateapathy, 2, 16));

        }

        @Override
        public int getModdedBiomeGrassColor(int original) {
            return 0xfffbfb;
        }

        @Override
        public int getModdedBiomeFoliageColor(int original) {
            return 0xfffbfb;
        }
    }