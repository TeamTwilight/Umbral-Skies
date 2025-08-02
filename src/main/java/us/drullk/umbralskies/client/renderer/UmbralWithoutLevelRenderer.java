package us.drullk.umbralskies.client.renderer;

import com.google.common.base.Suppliers;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import twilightforest.TwilightForestMod;
import twilightforest.client.event.ClientEvents;
import twilightforest.config.TFConfig;
import us.drullk.umbralskies.UmbralSkies;
import us.drullk.umbralskies.item.UmbralItems;

import java.util.function.Supplier;

public class UmbralWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer {
    private final SliderTrophyRenderer sliderTrophyRenderer;
    private final ValkyrieQueenTrophyRenderer valkyrieQueenTrophyRenderer;
    private final SunSpiritTrophyRenderer sunSpiritTrophyRenderer;

    private final ModelResourceLocation bronzeBackplate = ModelResourceLocation.standalone(UmbralSkies.prefix("item/trophy_bronze"));
    private final ModelResourceLocation silverBackplate = ModelResourceLocation.standalone(TwilightForestMod.prefix("item/trophy_minor"));
    private final ModelResourceLocation goldBackplate = ModelResourceLocation.standalone(TwilightForestMod.prefix("item/trophy"));

    private static final Supplier<UmbralWithoutLevelRenderer> INSTANCE = Suppliers.memoize(() -> new UmbralWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()));

    public static final IClientItemExtensions ITEM_EXTENSION = Util.make(() -> new IClientItemExtensions() {
        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            return INSTANCE.get();
        }
    });

    private UmbralWithoutLevelRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);

        this.sliderTrophyRenderer = new SliderTrophyRenderer(modelSet);
        this.valkyrieQueenTrophyRenderer = new ValkyrieQueenTrophyRenderer(modelSet);
        this.sunSpiritTrophyRenderer = new SunSpiritTrophyRenderer(modelSet);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (stack.is(UmbralItems.SLIDER_TROPHY.get())) {
            render(stack, context, poseStack, bufferSource, packedLight, packedOverlay, this.sliderTrophyRenderer, this.bronzeBackplate);
        } else if (stack.is(UmbralItems.VALKYRIE_QUEEN_TROPHY.get())) {
            render(stack, context, poseStack, bufferSource, packedLight, packedOverlay, this.valkyrieQueenTrophyRenderer, this.silverBackplate);
        } else if (stack.is(UmbralItems.SUN_SPIRIT_TROPHY.get())) {
            render(stack, context, poseStack, bufferSource, packedLight, packedOverlay, this.sunSpiritTrophyRenderer, this.goldBackplate);
        }
    }

    private static void render(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, RenderWithoutEntity withoutEntity, ModelResourceLocation backplateLocation) {
        poseStack.pushPose();
        if (context == ItemDisplayContext.GUI) {
            poseStack.pushPose();
            BakedModel backplate = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getModelManager().getModel(backplateLocation);

            poseStack.translate(0.5f, 0.5f, -1.5f);

            var bufferSourceMain = Minecraft.getInstance().renderBuffers().bufferSource();

            Lighting.setupForFlatItems();
            Minecraft.getInstance().getItemRenderer().render(stack, context, false, poseStack, bufferSourceMain, 0xf000f0, OverlayTexture.NO_OVERLAY, backplate.applyTransform(context, poseStack, false));
            bufferSourceMain.endBatch();
            Lighting.setupFor3DItems();

            poseStack.popPose();

            poseStack.translate(0.5f, 0.5f, 0);
            poseStack.mulPose(Axis.XP.rotationDegrees(30));
            poseStack.mulPose(Axis.YN.rotationDegrees(TFConfig.rotateTrophyHeadsGui && !Minecraft.getInstance().isPaused() ? ClientEvents.time % 360 : -45));
            poseStack.translate(-0.5f, -0.25f, -0.5f);
        }

        withoutEntity.render(poseStack, bufferSource, packedLight, packedOverlay, false);

        poseStack.popPose();
    }
}
