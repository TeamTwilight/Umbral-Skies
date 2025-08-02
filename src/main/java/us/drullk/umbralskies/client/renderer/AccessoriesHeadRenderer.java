package us.drullk.umbralskies.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class AccessoriesHeadRenderer implements AccessoryRenderer {
	@Override
	public <M extends LivingEntity> void render(ItemStack item, SlotReference reference, PoseStack stack, EntityModel<M> model, MultiBufferSource multiBufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (model instanceof HeadedModel headModel) {
			stack.pushPose();
			headModel.getHead().translateAndRotate(stack);
			stack.translate(0.0D, -0.25D, 0.0D);
			stack.mulPose(Axis.YP.rotationDegrees(180.0F));
			stack.scale(0.625F, -0.625F, -0.625F);
			ItemInHandRenderer renderer = new ItemInHandRenderer(Minecraft.getInstance(), Minecraft.getInstance().getEntityRenderDispatcher(), Minecraft.getInstance().getItemRenderer());
			renderer.renderItem(reference.entity(), item, ItemDisplayContext.HEAD, false, stack, multiBufferSource, light);
			stack.popPose();
		}
	}
}
