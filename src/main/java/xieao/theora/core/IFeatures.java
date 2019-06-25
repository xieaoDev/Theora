package xieao.theora.core;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xieao.theora.world.gen.WoodFeature;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class IFeatures {
    public static final List<Feature<?>> FEATURES = new ArrayList<>();
    public static final Feature<NoFeatureConfig> WOOD_FEATURE;

    static {
        WOOD_FEATURE = register("wood", new WoodFeature(NoFeatureConfig::deserialize));
    }

    public static void register() {
        Biome.BIOMES.forEach(biome -> {
            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST) || biome.getCategory() == Biome.Category.FOREST) {
                ConfiguredFeature<?> configuredFeature = Biome.createDecoratedFeature(WOOD_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(800));
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, configuredFeature);
            }
        });
    }

    static Feature<NoFeatureConfig> register(String id, Feature<NoFeatureConfig> feature) {
        feature.setRegistryName(id);
        FEATURES.add(feature);
        return feature;
    }

    @SubscribeEvent
    public static void onRegistry(RegistryEvent.Register<Feature<?>> event) {
        FEATURES.forEach(feature -> event.getRegistry().register(feature));
    }
}