package us.drullk.umbralskies.client.renderer;

import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class EmissiveGlovesRenderer extends GlovesRenderer {

	@Override
	public <M extends LivingEntity> void render(ItemStack stack, SlotReference reference, PoseStack poseStack, EntityModel<M> entityModel, MultiBufferSource buffer, int packedLight, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		super.render(stack, reference, poseStack, entityModel, buffer, LightTexture.FULL_BRIGHT, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderFirstPerson(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, HumanoidModel<?> humanoidModel, HumanoidArm arm) {
		super.renderFirstPerson(stack, poseStack, buffer, LightTexture.FULL_BRIGHT, player, humanoidModel, arm);
	}
}
