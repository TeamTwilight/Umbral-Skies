package us.drullk.umbralskies.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import us.drullk.umbralskies.block.entity.AetherTrophyEntity;

import java.util.function.Supplier;

public abstract class AbstractAetherTrophyBlock extends BaseEntityBlock {
    private final MapCodec<AbstractAetherTrophyBlock> codec;

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    @Nullable
    private final Supplier<SoundEvent> clickSound;
    public final Supplier<BlockEntityType<AetherTrophyEntity>> blockEntityType;

    protected AbstractAetherTrophyBlock(Properties properties, @Nullable Supplier<SoundEvent> clickSound, Supplier<BlockEntityType<AetherTrophyEntity>> blockEntityType) {
        super(properties);
        this.clickSound = clickSound;
        this.blockEntityType = blockEntityType;
        this.codec = MapCodec.unit(this);
        this.registerDefaultState(this.getStateDefinition().any().setValue(POWERED, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter getter, BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return this.blockEntityType.get().create(pos, state);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide()) {
            boolean flag = level.hasNeighborSignal(pos);
            if (flag != state.getValue(POWERED)) {
                if (flag) {
                    this.playSound(level, pos);
                }
                level.setBlockAndUpdate(pos, state.setValue(POWERED, flag));
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        this.playSound(level, pos);
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    public void playSound(Level level, BlockPos pos) {
        if (!level.isClientSide() && this.clickSound != null) {
            level.playSound(null, pos, this.clickSound.get(), SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return this.codec;
    }
}
