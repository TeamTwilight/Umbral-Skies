package us.drullk.umbralskies.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import us.drullk.umbralskies.block.UmbralBlocks;

public class AetherTrophyEntity extends BlockEntity {
	public static AetherTrophyEntity slider(BlockPos pos, BlockState state) {
		return new AetherTrophyEntity(UmbralBlocks.SLIDER_TROPHY_ENTITY.get(), pos, state);
	}

	public static AetherTrophyEntity valkyrie(BlockPos pos, BlockState state) {
		return new AetherTrophyEntity(UmbralBlocks.VALKYRIE_TROPHY_ENTITY.get(), pos, state);
	}

	public static AetherTrophyEntity sunSpirit(BlockPos pos, BlockState state) {
		return new AetherTrophyEntity(UmbralBlocks.SUN_SPIRIT_TROPHY_ENTITY.get(), pos, state);
	}

	public AetherTrophyEntity(BlockEntityType<AetherTrophyEntity> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}
}
