package io.github.blaezdev.rwbym.world.biome;

import io.github.blaezdev.rwbym.entity.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.EntityViewRenderEvent;

public class BiomeDomainofLight extends Biome {

    public BiomeDomainofLight() {
        super(new BiomeProperties("Domain of Light").setBaseBiome("forest").setRainfall(0.5F).setHeightVariation(0.01F));
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.decorator.treesPerChunk = 0;
        this.decorator.grassPerChunk = 400;
        this.decorator.flowersPerChunk = 100;
        this.decorator.sandPatchesPerChunk = 2;
        this.decorator.gravelPatchesPerChunk = 10;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return 0xF1BB3C;
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return 0xF1BB3C;
    }

    @Override
    public int getWaterColorMultiplier() {return 0x5C97C5; }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xB2C3D8;
    }
}