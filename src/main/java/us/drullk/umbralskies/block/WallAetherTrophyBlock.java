package us.drullk.umbralskies.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import us.drullk.umbralskies.block.entity.AetherTrophyEntity;

import java.util.function.Supplier;

public class WallAetherTrophyBlock extends AbstractAetherTrophyBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape NORTH = Block.box(4.0D, 4.0D, 8.0D, 12.0D, 12.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 8.0D);
    protected static final VoxelShape EAST = Block.box(0.0D, 4.0D, 4.0D, 8.0D, 12.0D, 12.0D);
    protected static final VoxelShape WEST = Block.box(8.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);

    public WallAetherTrophyBlock(Properties properties, @Nullable Supplier<SoundEvent> clickSound, Supplier<BlockEntityType<AetherTrophyEntity>> blockEntityType) {
        super(properties, clickSound, blockEntityType);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = super.getStateForPlacement(context);
        BlockGetter blockgetter = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction[] nearestOrderedDirections = context.getNearestLookingDirections();

        for (Direction direction : nearestOrderedDirections) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.setValue(FACING, direction.getOpposite());

                if (!blockgetter.getBlockState(blockpos.relative(direction)).canBeReplaced(context))
                    return blockstate;
            }
        }

        return null;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
            default -> NORTH;
        };
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(FACING));
    }

    @Override
    public String getDescriptionId() {
        return this.asItem().getDescriptionId();
    }
}
