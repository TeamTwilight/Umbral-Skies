package us.drullk.umbralskies.data;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import twilightforest.TwilightForestMod;
import twilightforest.block.BanisterBlock;
import twilightforest.block.ClimbableHollowLogBlock;
import twilightforest.block.HorizontalHollowLogBlock;
import twilightforest.block.VerticalHollowLogBlock;
import us.drullk.umbralskies.block.UmbralBlocks;
import us.drullk.umbralskies.UmbralKeys;
import us.drullk.umbralskies.UmbralSkies;

public class UmbralBlockStateModels extends BlockStateProvider {
	public UmbralBlockStateModels(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, UmbralSkies.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.banister(UmbralBlocks.SKYROOT_BANISTER.get());

		var models = this.models();

		this.simpleBlock(UmbralBlocks.SLIDER_TROPHY_BLOCK.get(), models.getExistingFile(ResourceLocation.withDefaultNamespace("block/skull")));
		this.simpleBlock(UmbralBlocks.VALKYRIE_QUEEN_TROPHY_BLOCK.get(), models.getExistingFile(ResourceLocation.withDefaultNamespace("block/skull")));
		this.simpleBlock(UmbralBlocks.SUN_SPIRIT_TROPHY_BLOCK.get(), models.getExistingFile(ResourceLocation.withDefaultNamespace("block/skull")));
		this.simpleBlock(UmbralBlocks.SLIDER_WALL_TROPHY_BLOCK.get(), models.getExistingFile(ResourceLocation.withDefaultNamespace("block/skull")));
		this.simpleBlock(UmbralBlocks.VALKYRIE_WALL_QUEEN_TROPHY_BLOCK.get(), models.getExistingFile(ResourceLocation.withDefaultNamespace("block/skull")));
		this.simpleBlock(UmbralBlocks.SUN_SPIRIT_WALL_TROPHY_BLOCK.get(), models.getExistingFile(ResourceLocation.withDefaultNamespace("block/skull")));

		ModelFile.ExistingModelFile horizontalHollowLog = models.getExistingFile(TwilightForestMod.prefix("horizontal_hollow_log"));
		ModelFile.ExistingModelFile hollowLogMossGrass = models.getExistingFile(TwilightForestMod.prefix("hollow_log_moss_grass"));
		ModelFile.ExistingModelFile hollowLogMoss = models.getExistingFile(TwilightForestMod.prefix("hollow_log_moss"));
		ModelFile.ExistingModelFile hollowLogSnow = models.getExistingFile(TwilightForestMod.prefix("hollow_log_snow"));
		ModelFile.ExistingModelFile verticalHollowLog = models.getExistingFile(TwilightForestMod.prefix("vertical_hollow_log"));
		ModelFile.ExistingModelFile verticalHollowLogVine = models.getExistingFile(TwilightForestMod.prefix("vertical_hollow_log_vine"));
		ModelFile.ExistingModelFile verticalHollowLogLadder = models.getExistingFile(TwilightForestMod.prefix("vertical_hollow_log_ladder"));

		this.aetherHollowLogs(
			AetherBlocks.SKYROOT_LOG,
			AetherBlocks.STRIPPED_SKYROOT_LOG,
			ResourceLocation.fromNamespaceAndPath(Aether.MODID, "block/natural/skyroot_log_top"),
			UmbralBlocks.HOLLOW_SKYROOT_LOG_HORIZONTAL,
			UmbralBlocks.HOLLOW_SKYROOT_LOG_VERTICAL,
			UmbralBlocks.HOLLOW_SKYROOT_LOG_CLIMBABLE,
			horizontalHollowLog,
			hollowLogMossGrass,
			hollowLogMoss,
			hollowLogSnow,
			verticalHollowLog,
			verticalHollowLogVine,
			verticalHollowLogLadder
		);

		this.aetherHollowLogs(
			AetherBlocks.GOLDEN_OAK_LOG,
			AetherBlocks.STRIPPED_SKYROOT_LOG,
			ResourceLocation.fromNamespaceAndPath(Aether.MODID, "block/natural/skyroot_log_top"),
			UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_HORIZONTAL,
			UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_VERTICAL,
			UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_CLIMBABLE,
			horizontalHollowLog,
			hollowLogMossGrass,
			hollowLogMoss,
			hollowLogSnow,
			verticalHollowLog,
			verticalHollowLogVine,
			verticalHollowLogLadder
		);
	}

	private void banister(BanisterBlock banister) {
		this.getVariantBuilder(banister).forAllStatesExcept(state -> {
			Direction facing = state.getValue(BanisterBlock.FACING);
			int yRot = (int) facing.toYRot();
			String extended = state.getValue(BanisterBlock.EXTENDED) ? "_extended" : "";
			String variant = state.getValue(BanisterBlock.SHAPE).getSerializedName() + extended;

			return ConfiguredModel.builder().modelFile(this.models().withExistingParent("block/" + this.getBlockName(banister) + "_" + variant, TwilightForestMod.prefix("banister_" + variant)).texture("texture", UmbralKeys.SKYROOT_BANISTER_TEX)).rotationY(yRot).build();
		}, BanisterBlock.WATERLOGGED);
	}


	private static final ResourceLocation CUTOUT = ResourceLocation.withDefaultNamespace("cutout");

	private void aetherHollowLogs(DeferredHolder<Block, RotatedPillarBlock> originalLog, DeferredHolder<Block, RotatedPillarBlock> strippedLog, ResourceLocation top, DeferredHolder<Block, HorizontalHollowLogBlock> horizontalHollowLog, DeferredHolder<Block, VerticalHollowLogBlock> verticalHollowLog, DeferredHolder<Block, ClimbableHollowLogBlock> climbableHollowLog, ModelFile emptyLog, ModelFile mossLog, ModelFile grassLog, ModelFile snowLog, ModelFile hollowLog, ModelFile vineLog, ModelFile ladderLog) {
		ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Aether.MODID, "block/natural/" + originalLog.getId().getPath());
		ResourceLocation inner = ResourceLocation.fromNamespaceAndPath(Aether.MODID, "block/natural/" + strippedLog.getId().getPath());

		this.getVariantBuilder(horizontalHollowLog.get()).forAllStates(state -> ConfiguredModel.builder().modelFile((switch (state.getValue(HorizontalHollowLogBlock.VARIANT)) {
			case MOSS -> this.models().getBuilder(horizontalHollowLog.getId().getPath() + "_moss").parent(mossLog);
			case MOSS_AND_GRASS -> this.models().getBuilder(horizontalHollowLog.getId().getPath() + "_moss_grass").parent(grassLog);
			case SNOW -> this.models().getBuilder(horizontalHollowLog.getId().getPath() + "_snow").parent(snowLog);
			default -> this.models().getBuilder(horizontalHollowLog.getId().getPath()).parent(emptyLog);
		}).renderType(CUTOUT).texture("top", top).texture("side", side).texture("inner", inner)).rotationY(state.getValue(HorizontalHollowLogBlock.HORIZONTAL_AXIS) == Direction.Axis.X ? 90 : 0).build());

		this.simpleBlock(verticalHollowLog.get(), this.models().getBuilder(verticalHollowLog.getId().getPath()).parent(hollowLog).texture("top", top).texture("side", side).texture("inner", inner));

		this.getVariantBuilder(climbableHollowLog.get()).forAllStates(state -> ConfiguredModel.builder().modelFile((switch (state.getValue(ClimbableHollowLogBlock.VARIANT)) {
			case VINE -> this.models().getBuilder(climbableHollowLog.getId().getPath() + "_vine").parent(vineLog);
			case LADDER, LADDER_WATERLOGGED -> this.models().getBuilder(climbableHollowLog.getId().getPath() + "_ladder").parent(ladderLog);
		}).renderType(CUTOUT).texture("top", top).texture("side", side).texture("inner", inner)).rotationY((int) state.getValue(ClimbableHollowLogBlock.FACING).toYRot()).uvLock(true).build());
	}

	private String getBlockName(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block).getPath();
	}
}
