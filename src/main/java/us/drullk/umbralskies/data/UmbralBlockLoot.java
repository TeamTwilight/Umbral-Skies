package us.drullk.umbralskies.data;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import twilightforest.block.ClimbableHollowLogBlock;
import twilightforest.block.HorizontalHollowLogBlock;
import twilightforest.block.VerticalHollowLogBlock;
import twilightforest.enums.HollowLogVariants;
import twilightforest.init.TFBlocks;
import us.drullk.umbralskies.UmbralSkies;
import us.drullk.umbralskies.block.FloorAetherTrophyBlock;
import us.drullk.umbralskies.block.UmbralBlocks;
import us.drullk.umbralskies.block.WallAetherTrophyBlock;
import us.drullk.umbralskies.item.AetherTrophyItem;
import us.drullk.umbralskies.item.UmbralItems;

import java.util.Set;
import java.util.stream.Stream;

public class UmbralBlockLoot extends BlockLootSubProvider {

	public UmbralBlockLoot(HolderLookup.Provider provider) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
	}

	@Override
	protected void generate() {
		this.dropSelf(UmbralBlocks.SKYROOT_BANISTER.get());

		this.hollowLogs(UmbralBlocks.HOLLOW_SKYROOT_LOG_HORIZONTAL, UmbralBlocks.HOLLOW_SKYROOT_LOG_VERTICAL, UmbralBlocks.HOLLOW_SKYROOT_LOG_CLIMBABLE);
		this.hollowLogs(UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_HORIZONTAL, UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_VERTICAL, UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_CLIMBABLE);

		this.trophy(UmbralItems.SLIDER_TROPHY, UmbralBlocks.SLIDER_TROPHY_BLOCK, UmbralBlocks.SLIDER_WALL_TROPHY_BLOCK);
		this.trophy(UmbralItems.VALKYRIE_QUEEN_TROPHY, UmbralBlocks.VALKYRIE_QUEEN_TROPHY_BLOCK, UmbralBlocks.VALKYRIE_WALL_QUEEN_TROPHY_BLOCK);
		this.trophy(UmbralItems.SUN_SPIRIT_TROPHY, UmbralBlocks.SUN_SPIRIT_TROPHY_BLOCK, UmbralBlocks.SUN_SPIRIT_WALL_TROPHY_BLOCK);
	}

	private void trophy(DeferredHolder<Item, AetherTrophyItem> itemTrophy, DeferredHolder<Block, FloorAetherTrophyBlock> floorTrophy, DeferredHolder<Block, WallAetherTrophyBlock> wallTrophy) {
		var table = this.createSingleItemTable(itemTrophy.get());
		this.add(floorTrophy.get(), table);
		this.add(wallTrophy.get(), table);
	}

	private void hollowLogs(DeferredHolder<Block, HorizontalHollowLogBlock> horizontalLog, DeferredHolder<Block, VerticalHollowLogBlock> verticalLog, DeferredHolder<Block, ClimbableHollowLogBlock> climbable) {
		this.add(horizontalLog.get(), this.horizontalHollowLog(horizontalLog.get()));
		this.add(verticalLog.get(), this.verticalHollowLog(verticalLog.get()));
		this.add(climbable.get(), this.climbableHollowLog(climbable.get()));
	}

	private LootTable.Builder horizontalHollowLog(HorizontalHollowLogBlock log) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(log.asItem()).when(this.hasSilkTouch()).otherwise(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))).apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Blocks.SHORT_GRASS).when(this.hasSilkTouch()).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(log).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HorizontalHollowLogBlock.VARIANT, HollowLogVariants.Horizontal.MOSS_AND_GRASS)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(TFBlocks.MOSS_PATCH.get()).when(this.hasSilkTouch()).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(log).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HorizontalHollowLogBlock.VARIANT, HollowLogVariants.Horizontal.MOSS_AND_GRASS)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(TFBlocks.MOSS_PATCH.get()).when(this.hasSilkTouch()).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(log).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HorizontalHollowLogBlock.VARIANT, HollowLogVariants.Horizontal.MOSS)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Items.SNOWBALL).when(this.hasSilkTouch()).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(log).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HorizontalHollowLogBlock.VARIANT, HollowLogVariants.Horizontal.SNOW)))));
	}

	private LootTable.Builder verticalHollowLog(VerticalHollowLogBlock log) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(log.asItem()).when(this.hasSilkTouch()).otherwise(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))).apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));
	}

	private LootTable.Builder climbableHollowLog(ClimbableHollowLogBlock log) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(log.asItem()).when(this.hasSilkTouch()).otherwise(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))).apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Blocks.VINE).when(this.hasSilkTouch()).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(log).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ClimbableHollowLogBlock.VARIANT, HollowLogVariants.Climbable.VINE)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Blocks.LADDER).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(log).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ClimbableHollowLogBlock.VARIANT, HollowLogVariants.Climbable.LADDER)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Blocks.LADDER).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(log).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ClimbableHollowLogBlock.VARIANT, HollowLogVariants.Climbable.LADDER_WATERLOGGED)))));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		Stream<Block> iterator = BuiltInRegistries.BLOCK.stream();
		return iterator.filter(entry -> UmbralSkies.MODID.equals(entry.builtInRegistryHolder().key().location().getNamespace())).toList();
	}
}
