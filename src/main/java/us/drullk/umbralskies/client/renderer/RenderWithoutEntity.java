package us.drullk.umbralskies.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import us.drullk.umbralskies.block.FloorAetherTrophyBlock;
import us.drullk.umbralskies.block.WallAetherTrophyBlock;
import us.drullk.umbralskies.block.entity.AetherTrophyEntity;

public interface RenderWithoutEntity {
    default void render(AetherTrophyEntity trophy, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int overlay, boolean powered) {
        BlockState state = trophy.getBlockState();
        boolean isWall = state.getBlock() instanceof WallAetherTrophyBlock;
        float rotation = RotationSegment.convertToDegrees(isWall ? RotationSegment.convertToSegment(state.getValue(WallAetherTrophyBlock.FACING).getOpposite()) : state.getValue(FloorAetherTrophyBlock.ROTATION));

        poseStack.pushPose();

        poseStack.translate(0.5, 0, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(180 - rotation));
        poseStack.translate(-0.5, 0, -0.5);

        if (isWall) poseStack.translate(0, 0.25, -0.25);

        this.render(poseStack, bufferSource, packedLight, overlay, powered);

        poseStack.popPose();
    }

    void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int overlay, boolean powered);
}
