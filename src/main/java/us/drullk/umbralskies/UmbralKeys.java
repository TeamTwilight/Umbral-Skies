package us.drullk.umbralskies;

import com.aetherteam.aether.Aether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import twilightforest.TFRegistries;
import twilightforest.util.woods.WoodPalette;

public class UmbralKeys {
	public static final ResourceKey<WoodPalette> SKYROOT_PALETTE = ResourceKey.create(TFRegistries.Keys.WOOD_PALETTES, UmbralSkies.prefix("skyroot"));

	public static final ResourceKey<ConfiguredFeature<?, ?>> AETHER_HUT_PALETTE = ResourceKey.create(Registries.CONFIGURED_FEATURE, UmbralSkies.prefix("aether_palette_hut"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> AETHER_SIMPLE_WELL_PALETTE = ResourceKey.create(Registries.CONFIGURED_FEATURE, UmbralSkies.prefix("aether_palette_simple_well"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> AETHER_FANCY_WELL_PALETTE = ResourceKey.create(Registries.CONFIGURED_FEATURE, UmbralSkies.prefix("aether_palette_fancy_well"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> RANDOMIZED_AETHER_WELL = ResourceKey.create(Registries.CONFIGURED_FEATURE, UmbralSkies.prefix("randomized_aether_well"));

	public static final ResourceKey<PlacedFeature> AETHER_DRUID_HUT = ResourceKey.create(Registries.PLACED_FEATURE, UmbralSkies.prefix("aether_druid_hut"));
	public static final ResourceKey<PlacedFeature> PLACEABLE_AETHER_WELL = ResourceKey.create(Registries.PLACED_FEATURE, UmbralSkies.prefix("aether_well"));

	public static final ResourceKey<BiomeModifier> AETHER_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, UmbralSkies.prefix("aether_modifier"));

	public static final ResourceLocation SKYROOT_BANISTER_TEX = ResourceLocation.fromNamespaceAndPath(Aether.MODID, "block/construction/skyroot_planks");
}
