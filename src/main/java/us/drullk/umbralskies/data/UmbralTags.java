package us.drullk.umbralskies.data;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import twilightforest.data.tags.BlockTagGenerator;
import us.drullk.umbralskies.block.UmbralBlocks;
import us.drullk.umbralskies.item.UmbralItems;
import us.drullk.umbralskies.UmbralKeys;
import us.drullk.umbralskies.UmbralSkies;

import java.util.concurrent.CompletableFuture;

public class UmbralTags {
	public static final TagKey<Block> AETHER_WORLDGEN = TagKey.create(Registries.BLOCK, UmbralSkies.prefix("generation_replaceable"));
	public static final TagKey<Block> ORE_BEARING_GROUND_HOLYSTONE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ore_bearing_ground/holystone"));
	public static final TagKey<Block> ORES_IN_GROUND_HOLYSTONE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores_in_ground/holystone"));
	public static final TagKey<PlacedFeature> ADDED_AETHER_FEATURES = TagKey.create(Registries.PLACED_FEATURE, UmbralSkies.prefix("twilight_to_aether_features"));

	public static class UmbralPlacedFeatureTags extends TagsProvider<PlacedFeature> {
		protected UmbralPlacedFeatureTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
			super(output, Registries.PLACED_FEATURE, provider, UmbralSkies.MODID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(ADDED_AETHER_FEATURES).add(UmbralKeys.AETHER_DRUID_HUT, UmbralKeys.PLACEABLE_AETHER_WELL);
		}
	}

	public static class UmbralBlockTags extends BlockTagsProvider {
		public UmbralBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
			super(output, provider, UmbralSkies.MODID, existingFileHelper);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(AETHER_WORLDGEN).addTags(BlockTags.SCULK_REPLACEABLE_WORLD_GEN, Tags.Blocks.STONES, BlockTags.DIRT, BlockTagGenerator.WORLDGEN_REPLACEABLES);

			this.tag(BlockTagGenerator.BANISTERS).add(UmbralBlocks.SKYROOT_BANISTER.get());

			this.tag(BlockTagGenerator.HOLLOW_LOGS_HORIZONTAL).add(UmbralBlocks.HOLLOW_SKYROOT_LOG_HORIZONTAL.get(), UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_HORIZONTAL.get());
			this.tag(BlockTagGenerator.HOLLOW_LOGS_VERTICAL).add(UmbralBlocks.HOLLOW_SKYROOT_LOG_VERTICAL.get(), UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_VERTICAL.get());
			this.tag(BlockTagGenerator.HOLLOW_LOGS_CLIMBABLE).add(UmbralBlocks.HOLLOW_SKYROOT_LOG_CLIMBABLE.get(), UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_CLIMBABLE.get());

			this.tag(ORE_BEARING_GROUND_HOLYSTONE).add(AetherBlocks.HOLYSTONE.get());

			this.tag(ORES_IN_GROUND_HOLYSTONE).add(AetherBlocks.AMBROSIUM_ORE.get());
			this.tag(ORES_IN_GROUND_HOLYSTONE).add(AetherBlocks.ZANITE_ORE.get());
			this.tag(ORES_IN_GROUND_HOLYSTONE).add(AetherBlocks.GRAVITITE_ORE.get());
		}
	}

	public static class UmbralItemTags extends ItemTagsProvider {
		public UmbralItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockLookup, @Nullable ExistingFileHelper existingFileHelper) {
			super(output, provider, blockLookup, UmbralSkies.MODID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(AetherTags.Items.ACCESSORIES_GLOVES).add(
				UmbralItems.NAGA_GLOVES.get(),
				UmbralItems.IRONWOOD_GLOVES.get(),
				UmbralItems.FIERY_GLOVES.get(),
				UmbralItems.STEELEAF_GLOVES.get(),
				UmbralItems.KNIGHTMETAL_GLOVES.get(),
				UmbralItems.PHANTOM_GLOVES.get(),
				UmbralItems.ARCTIC_GLOVES.get(),
				UmbralItems.YETI_GLOVES.get()
			);

			this.tag(ItemTags.DYEABLE).add(UmbralItems.ARCTIC_GLOVES.get());

			this.tag(ItemTags.TRIMMABLE_ARMOR).add(
				UmbralItems.NAGA_GLOVES.get(),
				UmbralItems.IRONWOOD_GLOVES.get(),
				UmbralItems.FIERY_GLOVES.get(),
				UmbralItems.STEELEAF_GLOVES.get(),
				UmbralItems.KNIGHTMETAL_GLOVES.get(),
				UmbralItems.PHANTOM_GLOVES.get(),
				UmbralItems.ARCTIC_GLOVES.get(),
				UmbralItems.YETI_GLOVES.get()
			);
		}
	}
}
