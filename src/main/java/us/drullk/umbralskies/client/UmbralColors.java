package us.drullk.umbralskies.client;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import twilightforest.block.ClimbableHollowLogBlock;
import twilightforest.enums.HollowLogVariants;
import twilightforest.item.ArcticArmorItem;
import us.drullk.umbralskies.block.UmbralBlocks;
import us.drullk.umbralskies.item.UmbralItems;

@EventBusSubscriber(value = Dist.CLIENT)
public class UmbralColors {
	@SubscribeEvent
	public static void blockColors(RegisterColorHandlersEvent.Block event) {
		event.register((state, tintGetter, pos, index) -> (index & 1) == 0 ? 0xFF_FF_FF : GrassColor.get(0.5, 1), UmbralBlocks.HOLLOW_SKYROOT_LOG_HORIZONTAL.get(), UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_HORIZONTAL.get());
		event.register((state, tintGetter, pos, index) -> {
			if (state.getValue(ClimbableHollowLogBlock.VARIANT) != HollowLogVariants.Climbable.VINE || (index & 1) == 0)
				return 0xFFFFFFFF;

			if (tintGetter != null && pos != null)
				return BiomeColors.getAverageFoliageColor(tintGetter, pos);

			return FoliageColor.getDefaultColor();
		}, UmbralBlocks.HOLLOW_SKYROOT_LOG_CLIMBABLE.get(), UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_CLIMBABLE.get());
	}

	@SubscribeEvent
	public static void itemColors(RegisterColorHandlersEvent.Item event) {
		event.register((stack, index) -> index != 1 ? -1 : DyedItemColor.getOrDefault(stack, ArcticArmorItem.DEFAULT_COLOR), UmbralItems.ARCTIC_GLOVES.get());
	}
}
