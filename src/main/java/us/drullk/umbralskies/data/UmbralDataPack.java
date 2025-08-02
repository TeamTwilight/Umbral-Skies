package us.drullk.umbralskies.data;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.world.placementmodifier.DungeonBlacklistFilter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import twilightforest.TFRegistries;
import twilightforest.data.tags.CustomTagGenerator;
import twilightforest.init.TFFeatures;
import twilightforest.util.woods.WoodPalette;
import twilightforest.world.components.feature.config.SwizzleConfig;
import us.drullk.umbralskies.block.UmbralBlocks;
import us.drullk.umbralskies.UmbralKeys;

import java.util.List;

public class UmbralDataPack {
	static final RegistrySetBuilder DATA_BUILDER = new RegistrySetBuilder()
		.add(TFRegistries.Keys.WOOD_PALETTES, UmbralDataPack::generateWoodPalettes)
		.add(Registries.CONFIGURED_FEATURE, UmbralDataPack::generateFeatureConfigurations)
		.add(Registries.PLACED_FEATURE, UmbralDataPack::generatePlacedFeatures)
		.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, UmbralDataPack::generateBiomeModifiers);

	static void generateWoodPalettes(BootstrapContext<WoodPalette> context) {
		context.register(UmbralKeys.SKYROOT_PALETTE, new WoodPalette(
			AetherBlocks.SKYROOT_PLANKS.get(),
			AetherBlocks.SKYROOT_STAIRS.get(),
			AetherBlocks.SKYROOT_SLAB.get(),
			AetherBlocks.SKYROOT_BUTTON.get(),
			AetherBlocks.SKYROOT_FENCE.get(),
			AetherBlocks.SKYROOT_FENCE_GATE.get(),
			AetherBlocks.SKYROOT_PRESSURE_PLATE.get(),
			UmbralBlocks.SKYROOT_BANISTER.get()
		));
	}

	static void generateFeatureConfigurations(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		HolderGetter<WoodPalette> woodPaletteGetter = context.lookup(TFRegistries.Keys.WOOD_PALETTES);

		WeightedRandomList<WeightedEntry.Wrapper<HolderSet<WoodPalette>>> paletteList = buildPaletteChoices(woodPaletteGetter);

		ProcessorRule processorAetherDirt = new ProcessorRule(new BlockMatchTest(Blocks.DIRT), AlwaysTrueTest.INSTANCE, AetherBlocks.AETHER_DIRT.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true));
		ProcessorRule processorAetherGrass = new ProcessorRule(new BlockMatchTest(Blocks.GRASS_BLOCK), AlwaysTrueTest.INSTANCE, AetherBlocks.AETHER_GRASS_BLOCK.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true));
		ProcessorRule processorHolystone = new ProcessorRule(new BlockMatchTest(Blocks.STONE), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true));
		ProcessorRule processorLogs = new ProcessorRule(new TagMatchTest(BlockTags.LOGS), AlwaysTrueTest.INSTANCE, AetherBlocks.SKYROOT_LOG.get().defaultBlockState());

		// Cobblestone -> Holystone
		ProcessorRule cobble2HolystoneBlock = new ProcessorRule(new BlockMatchTest(Blocks.COBBLESTONE), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true));
		ProcessorRule cobble2HolystoneStair = new ProcessorRule(new BlockMatchTest(Blocks.COBBLESTONE_STAIRS), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_STAIRS.get().defaultBlockState());
		ProcessorRule cobble2HolystoneSlab = new ProcessorRule(new BlockMatchTest(Blocks.COBBLESTONE_SLAB), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_SLAB.get().defaultBlockState());
		ProcessorRule cobble2HolystoneWall = new ProcessorRule(new BlockMatchTest(Blocks.COBBLESTONE_WALL), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_WALL.get().defaultBlockState());

		// Stone Bricks -> Holystone
		ProcessorRule stoneBrick2HolystoneBlock = new ProcessorRule(new BlockMatchTest(Blocks.STONE_BRICKS), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE.get().defaultBlockState());
		ProcessorRule stoneBrick2HolystoneStair = new ProcessorRule(new BlockMatchTest(Blocks.STONE_BRICK_STAIRS), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_STAIRS.get().defaultBlockState());
		ProcessorRule stoneBrick2HolystoneSlab = new ProcessorRule(new BlockMatchTest(Blocks.STONE_BRICK_SLAB), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_SLAB.get().defaultBlockState());
		ProcessorRule stoneBrick2HolystoneWall = new ProcessorRule(new BlockMatchTest(Blocks.STONE_BRICK_WALL), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_WALL.get().defaultBlockState());
		ProcessorRule stoneChiseled2HolystoneBlock = new ProcessorRule(new BlockMatchTest(Blocks.CHISELED_STONE_BRICKS), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE.get().defaultBlockState());

		// Stone Bricks -> Holystone Bricks
		ProcessorRule stoneBrick2HolyBrickBlock = new ProcessorRule(new BlockMatchTest(Blocks.STONE_BRICKS), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_BRICKS.get().defaultBlockState());
		ProcessorRule stoneBrick2HolyBrickStair = new ProcessorRule(new BlockMatchTest(Blocks.STONE_BRICK_STAIRS), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_BRICK_STAIRS.get().defaultBlockState());
		ProcessorRule stoneBrick2HolyBrickSlab = new ProcessorRule(new BlockMatchTest(Blocks.STONE_BRICK_SLAB), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_BRICK_SLAB.get().defaultBlockState());
		ProcessorRule stoneBrick2HolyBrickWall = new ProcessorRule(new BlockMatchTest(Blocks.STONE_BRICK_WALL), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_BRICK_WALL.get().defaultBlockState());

		// (Red) Bricks -> Holystone Bricks
		ProcessorRule brick2HolyBlock = new ProcessorRule(new BlockMatchTest(Blocks.BRICKS), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_BRICKS.get().defaultBlockState());
		ProcessorRule brick2HolyStair = new ProcessorRule(new BlockMatchTest(Blocks.BRICK_STAIRS), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_BRICK_STAIRS.get().defaultBlockState());
		ProcessorRule brick2HolySlab = new ProcessorRule(new BlockMatchTest(Blocks.BRICK_SLAB), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_BRICK_SLAB.get().defaultBlockState());
		ProcessorRule brick2HolyWall = new ProcessorRule(new BlockMatchTest(Blocks.BRICK_WALL), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_BRICK_WALL.get().defaultBlockState());

		// For random conversions of Holystone to Mossy
		ProcessorRule randomHolyMossBlock = new ProcessorRule(new RandomBlockMatchTest(AetherBlocks.HOLYSTONE.get(), 0.25f), AlwaysTrueTest.INSTANCE, AetherBlocks.MOSSY_HOLYSTONE.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true));
		ProcessorRule randomHolyMossStair = new ProcessorRule(new RandomBlockMatchTest(AetherBlocks.HOLYSTONE_STAIRS.get(), 0.25f), AlwaysTrueTest.INSTANCE, AetherBlocks.MOSSY_HOLYSTONE_STAIRS.get().defaultBlockState());
		ProcessorRule randomHolyMossSlab = new ProcessorRule(new RandomBlockMatchTest(AetherBlocks.HOLYSTONE_SLAB.get(), 0.25f), AlwaysTrueTest.INSTANCE, AetherBlocks.MOSSY_HOLYSTONE_SLAB.get().defaultBlockState());
		ProcessorRule randomHolyMossWall = new ProcessorRule(new RandomBlockMatchTest(AetherBlocks.HOLYSTONE_WALL.get(), 0.25f), AlwaysTrueTest.INSTANCE, AetherBlocks.MOSSY_HOLYSTONE_WALL.get().defaultBlockState());

		SwizzleConfig aetherHutSwizzle = SwizzleConfig.generate(woodPaletteGetter, CustomTagGenerator.WoodPaletteTagGenerator.DRUID_HUT_SWIZZLE_MASK, paletteList,
			new ProcessorRule(new BlockMatchTest(Blocks.ANDESITE), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)),
			new ProcessorRule(new BlockMatchTest(Blocks.POLISHED_ANDESITE), AlwaysTrueTest.INSTANCE, AetherBlocks.HOLYSTONE_BRICKS.get().defaultBlockState()),
			new ProcessorRule(new TagMatchTest(BlockTags.LEAVES), AlwaysTrueTest.INSTANCE, AetherBlocks.SKYROOT_LEAVES.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)),
			new ProcessorRule(new TagMatchTest(BlockTags.FENCES), AlwaysTrueTest.INSTANCE, AetherBlocks.SKYROOT_FENCE.get().defaultBlockState()),
			processorLogs,
			cobble2HolystoneBlock,
			cobble2HolystoneStair,
			cobble2HolystoneSlab,
			cobble2HolystoneWall,
			brick2HolyBlock,
			brick2HolyStair,
			brick2HolySlab,
			brick2HolyWall,
			stoneBrick2HolystoneBlock,
			stoneBrick2HolystoneStair,
			stoneBrick2HolystoneSlab,
			stoneBrick2HolystoneWall,
			randomHolyMossBlock,
			randomHolyMossStair,
			randomHolyMossSlab,
			randomHolyMossWall
		);
		context.register(UmbralKeys.AETHER_HUT_PALETTE, new ConfiguredFeature<>(TFFeatures.DRUID_HUT.get(), aetherHutSwizzle));

		SwizzleConfig aetherSimpleWellSwizzle = SwizzleConfig.generate(woodPaletteGetter, CustomTagGenerator.WoodPaletteTagGenerator.WELL_SWIZZLE_MASK, paletteList,
			processorLogs,
			processorAetherDirt,
			processorAetherGrass,
			cobble2HolystoneBlock,
			cobble2HolystoneStair,
			cobble2HolystoneSlab,
			cobble2HolystoneWall,
			randomHolyMossBlock,
			randomHolyMossStair,
			randomHolyMossSlab,
			randomHolyMossWall
		);
		context.register(UmbralKeys.AETHER_SIMPLE_WELL_PALETTE, new ConfiguredFeature<>(TFFeatures.SIMPLE_WELL.get(), aetherSimpleWellSwizzle));

		SwizzleConfig aetherFancyWellSwizzle = SwizzleConfig.generate(woodPaletteGetter, CustomTagGenerator.WoodPaletteTagGenerator.WELL_SWIZZLE_MASK, paletteList,
			processorLogs,
			processorAetherDirt,
			processorAetherGrass,
			processorHolystone,
			cobble2HolystoneBlock,
			cobble2HolystoneStair,
			cobble2HolystoneSlab,
			cobble2HolystoneWall,
			stoneBrick2HolyBrickBlock,
			stoneBrick2HolyBrickStair,
			stoneBrick2HolyBrickSlab,
			stoneBrick2HolyBrickWall,
			stoneChiseled2HolystoneBlock,
			randomHolyMossBlock,
			randomHolyMossStair,
			randomHolyMossSlab,
			randomHolyMossWall
		);
		context.register(UmbralKeys.AETHER_FANCY_WELL_PALETTE, new ConfiguredFeature<>(TFFeatures.FANCY_WELL.get(), aetherFancyWellSwizzle));

		context.register(UmbralKeys.RANDOMIZED_AETHER_WELL, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(UmbralKeys.AETHER_FANCY_WELL_PALETTE)), 0.05f)), PlacementUtils.inlinePlaced(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(UmbralKeys.AETHER_SIMPLE_WELL_PALETTE)))));
	}

	private static void generatePlacedFeatures(BootstrapContext<PlacedFeature> context) {
		depthChecked(context, 15, 15, UmbralKeys.AETHER_DRUID_HUT, UmbralKeys.AETHER_HUT_PALETTE);
		depthChecked(context, 5, 15, UmbralKeys.PLACEABLE_AETHER_WELL, UmbralKeys.RANDOMIZED_AETHER_WELL);
	}

	private static void depthChecked(BootstrapContext<PlacedFeature> context, int dist, int depth, ResourceKey<PlacedFeature> aetherDruidHut, ResourceKey<ConfiguredFeature<?, ?>> aetherHutPalette) {
		int halfDist = dist >> 1;
		BlockPredicateFilter northTop = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(0, -1, halfDist), UmbralTags.AETHER_WORLDGEN));
		BlockPredicateFilter southTop = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(dist, -1, halfDist), UmbralTags.AETHER_WORLDGEN));
		BlockPredicateFilter westTop = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(halfDist, -1, 0), UmbralTags.AETHER_WORLDGEN));
		BlockPredicateFilter eastTop = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(halfDist, -1, dist), UmbralTags.AETHER_WORLDGEN));

		int middle = -(depth >> 1);
		BlockPredicateFilter middleSolidCheck1 = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(0, middle, 0), UmbralTags.AETHER_WORLDGEN));
		BlockPredicateFilter middleSolidCheck2 = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(dist, middle, 0), UmbralTags.AETHER_WORLDGEN));
		BlockPredicateFilter middleSolidCheck3 = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(0, middle, dist), UmbralTags.AETHER_WORLDGEN));
		BlockPredicateFilter middleSolidCheck4 = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(dist, middle, dist), UmbralTags.AETHER_WORLDGEN));

		BlockPredicateFilter undergroundSolidCheck1 = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(0, -depth, 0), UmbralTags.AETHER_WORLDGEN));
		BlockPredicateFilter undergroundSolidCheck2 = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(dist, -depth, 0), UmbralTags.AETHER_WORLDGEN));
		BlockPredicateFilter undergroundSolidCheck3 = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(0, -depth, dist), UmbralTags.AETHER_WORLDGEN));
		BlockPredicateFilter undergroundSolidCheck4 = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new BlockPos(dist, -depth, dist), UmbralTags.AETHER_WORLDGEN));

		context.register(aetherDruidHut, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(aetherHutPalette), List.of(RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, new DungeonBlacklistFilter(), northTop, southTop, westTop, eastTop, middleSolidCheck1, middleSolidCheck2, middleSolidCheck3, middleSolidCheck4, undergroundSolidCheck1, undergroundSolidCheck2, undergroundSolidCheck3, undergroundSolidCheck4)));
	}

	static void generateBiomeModifiers(BootstrapContext<BiomeModifier> context) {
		HolderSet<Biome> aetherBiomeTarget = context.lookup(Registries.BIOME).getOrThrow(AetherTags.Biomes.IS_AETHER);
		HolderSet<PlacedFeature> twilightFeatures = context.lookup(Registries.PLACED_FEATURE).getOrThrow(UmbralTags.ADDED_AETHER_FEATURES);

		context.register(UmbralKeys.AETHER_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(aetherBiomeTarget, twilightFeatures, GenerationStep.Decoration.SURFACE_STRUCTURES));
	}

	public static WeightedRandomList<WeightedEntry.Wrapper<HolderSet<WoodPalette>>> buildPaletteChoices(HolderGetter<WoodPalette> paletteHolders) {
		var skyroot = WeightedEntry.<HolderSet<WoodPalette>>wrap(HolderSet.direct(paletteHolders.getOrThrow(UmbralKeys.SKYROOT_PALETTE)), 24);
		var treasure = WeightedEntry.<HolderSet<WoodPalette>>wrap(paletteHolders.getOrThrow(CustomTagGenerator.WoodPaletteTagGenerator.TREASURE_PALETTES), 1);

		return WeightedRandomList.create(skyroot, treasure);
	}
}
