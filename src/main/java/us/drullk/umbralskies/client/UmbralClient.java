package us.drullk.umbralskies.client;

import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import twilightforest.item.ArcticArmorItem;
import us.drullk.umbralskies.UmbralSkies;
import us.drullk.umbralskies.block.UmbralBlocks;
import us.drullk.umbralskies.client.renderer.*;
import us.drullk.umbralskies.item.AetherTrophyItem;
import us.drullk.umbralskies.item.UmbralItems;

@EventBusSubscriber(value = Dist.CLIENT)
public class UmbralClient {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(UmbralBlocks.SLIDER_TROPHY_ENTITY.get(), SliderTrophyRenderer::new);
		BlockEntityRenderers.register(UmbralBlocks.VALKYRIE_TROPHY_ENTITY.get(), ValkyrieQueenTrophyRenderer::new);
		BlockEntityRenderers.register(UmbralBlocks.SUN_SPIRIT_TROPHY_ENTITY.get(), SunSpiritTrophyRenderer::new);
		registerCuriosRenderers();
	}

	@SubscribeEvent
	public static void registerExtraModels(ModelEvent.RegisterAdditional event) {
		event.register(ModelResourceLocation.standalone(UmbralSkies.prefix("item/trophy_bronze")));
	}

	@SubscribeEvent
	public static void registerExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(UmbralWithoutLevelRenderer.ITEM_EXTENSION, UmbralItems.SLIDER_TROPHY, UmbralItems.VALKYRIE_QUEEN_TROPHY, UmbralItems.SUN_SPIRIT_TROPHY);
		event.registerItem(new IClientItemExtensions() {
			@Override
			public int getDefaultDyeColor(ItemStack stack) {
				return ArcticArmorItem.DEFAULT_COLOR;
			}
		}, UmbralItems.ARCTIC_GLOVES);
	}

	private static void registerCuriosRenderers() {
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.NAGA_GLOVES.get(), GlovesRenderer::new);
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.IRONWOOD_GLOVES.get(), GlovesRenderer::new);
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.FIERY_GLOVES.get(), EmissiveGlovesRenderer::new);
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.STEELEAF_GLOVES.get(), GlovesRenderer::new);
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.KNIGHTMETAL_GLOVES.get(), GlovesRenderer::new);
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.PHANTOM_GLOVES.get(), GlovesRenderer::new);
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.ARCTIC_GLOVES.get(), GlovesRenderer::new);
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.YETI_GLOVES.get(), GlovesRenderer::new);

		AccessoriesRendererRegistry.registerRenderer(UmbralItems.SLIDER_TROPHY.get(), AccessoriesHeadRenderer::new);
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.VALKYRIE_QUEEN_TROPHY.get(), AccessoriesHeadRenderer::new);
		AccessoriesRendererRegistry.registerRenderer(UmbralItems.SUN_SPIRIT_TROPHY.get(), AccessoriesHeadRenderer::new);
	}

	@EventBusSubscriber(value = Dist.CLIENT)
	public static class Events {
		@SubscribeEvent
		public static void renderLiving(RenderLivingEvent.Pre<?, ?> event) {
			ItemStack stack = event.getEntity().getItemBySlot(EquipmentSlot.HEAD);

			if (!stackIsTrophy(stack)) return;
			if (!(event.getRenderer().getModel() instanceof HeadedModel headedModel)) return;

			headedModel.getHead().visible = false;

			if (!(headedModel instanceof HumanoidModel<?> humanoidModel)) return;

			humanoidModel.hat.visible = false;
		}

		private static boolean stackIsTrophy(ItemStack stack) {
			return stack.getItem() instanceof AetherTrophyItem;
		}
	}
}
