package us.drullk.umbralskies;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import us.drullk.umbralskies.block.UmbralBlocks;
import us.drullk.umbralskies.item.UmbralItems;

public class UmbralTab {

	public static final String TAB_LOCALE = "itemGroup.umbral_skies";

	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UmbralSkies.MODID);

	private static final Holder<CreativeModeTab> TAB = TABS.register("items", () -> CreativeModeTab.builder()
		.title(Component.translatable(TAB_LOCALE))
		.icon(() -> new ItemStack(UmbralItems.FIERY_GLOVES.get()))
		.displayItems((params, output) -> {
			output.accept(UmbralItems.SKYROOT_BANISTER.get());

			output.accept(UmbralItems.NAGA_GLOVES.get());
			output.accept(UmbralItems.IRONWOOD_GLOVES.get());
			output.accept(UmbralItems.FIERY_GLOVES.get());
			output.accept(UmbralItems.STEELEAF_GLOVES.get());
			output.accept(UmbralItems.KNIGHTMETAL_GLOVES.get());
			output.accept(UmbralItems.PHANTOM_GLOVES.get());
			output.accept(UmbralItems.ARCTIC_GLOVES.get());
			output.accept(UmbralItems.YETI_GLOVES.get());

			output.accept(UmbralItems.HOLLOW_SKYROOT_LOG.get());
			output.accept(UmbralItems.HOLLOW_GOLDEN_OAK_LOG.get());

			output.accept(UmbralItems.SLIDER_TROPHY.get());
			output.accept(UmbralItems.VALKYRIE_QUEEN_TROPHY.get());
			output.accept(UmbralItems.SUN_SPIRIT_TROPHY.get());
		}).build());
}
