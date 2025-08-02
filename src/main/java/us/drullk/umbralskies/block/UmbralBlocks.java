package us.drullk.umbralskies.block;

import com.aetherteam.aether.client.AetherSoundEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.block.BanisterBlock;
import twilightforest.block.ClimbableHollowLogBlock;
import twilightforest.block.HorizontalHollowLogBlock;
import twilightforest.block.VerticalHollowLogBlock;
import us.drullk.umbralskies.UmbralSkies;
import us.drullk.umbralskies.block.entity.AetherTrophyEntity;

public class UmbralBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, UmbralSkies.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, UmbralSkies.MODID);

    public static final DeferredHolder<Block, BanisterBlock> SKYROOT_BANISTER = BLOCKS.register("skyroot_banister", () -> new BanisterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final DeferredHolder<Block, HorizontalHollowLogBlock> HOLLOW_SKYROOT_LOG_HORIZONTAL = BLOCKS.register("hollow_skyroot_log_horizontal", () -> new HorizontalHollowLogBlock(Block.Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredHolder<Block, VerticalHollowLogBlock> HOLLOW_SKYROOT_LOG_VERTICAL = BLOCKS.register("hollow_skyroot_log_vertical", () -> new VerticalHollowLogBlock(Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2).sound(SoundType.WOOD).ignitedByLava(), UmbralBlocks.HOLLOW_SKYROOT_LOG_CLIMBABLE));
    public static final DeferredHolder<Block, ClimbableHollowLogBlock> HOLLOW_SKYROOT_LOG_CLIMBABLE = BLOCKS.register("hollow_skyroot_log_climbable", () -> new ClimbableHollowLogBlock(UmbralBlocks.HOLLOW_SKYROOT_LOG_VERTICAL, Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2).sound(SoundType.WOOD).ignitedByLava()));

    public static final DeferredHolder<Block, HorizontalHollowLogBlock> HOLLOW_GOLDEN_OAK_LOG_HORIZONTAL = BLOCKS.register("hollow_golden_oak_log_horizontal", () -> new HorizontalHollowLogBlock(Block.Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredHolder<Block, VerticalHollowLogBlock> HOLLOW_GOLDEN_OAK_LOG_VERTICAL = BLOCKS.register("hollow_golden_oak_log_vertical", () -> new VerticalHollowLogBlock(Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2).sound(SoundType.WOOD).ignitedByLava(), UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_CLIMBABLE));
    public static final DeferredHolder<Block, ClimbableHollowLogBlock> HOLLOW_GOLDEN_OAK_LOG_CLIMBABLE = BLOCKS.register("hollow_golden_oak_log_climbable", () -> new ClimbableHollowLogBlock(UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_VERTICAL, Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2).sound(SoundType.WOOD).ignitedByLava()));

    public static final DeferredHolder<Block, FloorAetherTrophyBlock> SLIDER_TROPHY_BLOCK = BLOCKS.register("slider_trophy", () -> new FloorAetherTrophyBlock(BlockBehaviour.Properties.of().instabreak().pushReaction(PushReaction.DESTROY), AetherSoundEvents.ENTITY_SLIDER_AMBIENT, UmbralBlocks.SLIDER_TROPHY_ENTITY));
    public static final DeferredHolder<Block, FloorAetherTrophyBlock> VALKYRIE_QUEEN_TROPHY_BLOCK = BLOCKS.register("valkyrie_queen_trophy", () -> new FloorAetherTrophyBlock(BlockBehaviour.Properties.of().instabreak().pushReaction(PushReaction.DESTROY), AetherSoundEvents.ENTITY_VALKYRIE_QUEEN_INTERACT, UmbralBlocks.VALKYRIE_TROPHY_ENTITY));
    public static final DeferredHolder<Block, FloorAetherTrophyBlock> SUN_SPIRIT_TROPHY_BLOCK = BLOCKS.register("sun_spirit_trophy", () -> new FloorAetherTrophyBlock(BlockBehaviour.Properties.of().instabreak().pushReaction(PushReaction.DESTROY), AetherSoundEvents.ENTITY_SUN_SPIRIT_INTERACT, UmbralBlocks.SUN_SPIRIT_TROPHY_ENTITY));
    public static final DeferredHolder<Block, WallAetherTrophyBlock> SLIDER_WALL_TROPHY_BLOCK = BLOCKS.register("slider_wall_trophy", () -> new WallAetherTrophyBlock(BlockBehaviour.Properties.of().instabreak().pushReaction(PushReaction.DESTROY), AetherSoundEvents.ENTITY_SLIDER_AMBIENT, UmbralBlocks.SLIDER_TROPHY_ENTITY));
    public static final DeferredHolder<Block, WallAetherTrophyBlock> VALKYRIE_WALL_QUEEN_TROPHY_BLOCK = BLOCKS.register("valkyrie_queen_wall_trophy", () -> new WallAetherTrophyBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instabreak(), AetherSoundEvents.ENTITY_VALKYRIE_QUEEN_INTERACT, UmbralBlocks.VALKYRIE_TROPHY_ENTITY));
    public static final DeferredHolder<Block, WallAetherTrophyBlock> SUN_SPIRIT_WALL_TROPHY_BLOCK = BLOCKS.register("sun_spirit_wall_trophy", () -> new WallAetherTrophyBlock(BlockBehaviour.Properties.of().instabreak().pushReaction(PushReaction.DESTROY), AetherSoundEvents.ENTITY_SUN_SPIRIT_INTERACT, UmbralBlocks.SUN_SPIRIT_TROPHY_ENTITY));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AetherTrophyEntity>> SLIDER_TROPHY_ENTITY = BLOCK_ENTITIES.register("slider_trophy", () -> BlockEntityType.Builder.of(AetherTrophyEntity::slider, UmbralBlocks.SLIDER_TROPHY_BLOCK.get(), UmbralBlocks.SLIDER_WALL_TROPHY_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AetherTrophyEntity>> VALKYRIE_TROPHY_ENTITY = BLOCK_ENTITIES.register("valkyrie_queen_trophy", () -> BlockEntityType.Builder.of(AetherTrophyEntity::valkyrie, UmbralBlocks.VALKYRIE_QUEEN_TROPHY_BLOCK.get(), UmbralBlocks.VALKYRIE_WALL_QUEEN_TROPHY_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AetherTrophyEntity>> SUN_SPIRIT_TROPHY_ENTITY = BLOCK_ENTITIES.register("sun_spirit_trophy", () -> BlockEntityType.Builder.of(AetherTrophyEntity::sunSpirit, UmbralBlocks.SUN_SPIRIT_TROPHY_BLOCK.get(), UmbralBlocks.SUN_SPIRIT_WALL_TROPHY_BLOCK.get()).build(null));
}
