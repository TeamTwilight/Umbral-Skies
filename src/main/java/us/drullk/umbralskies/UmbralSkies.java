package us.drullk.umbralskies;

import io.wispforest.accessories.api.AccessoriesAPI;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.drullk.umbralskies.block.UmbralBlocks;
import us.drullk.umbralskies.data.UmbralData;
import us.drullk.umbralskies.item.UmbralItems;
import us.drullk.umbralskies.loottables.UmbralLootModifiers;

@Mod(UmbralSkies.MODID)
public class UmbralSkies {
	public static final Logger LOGGER = LoggerFactory.getLogger(UmbralSkies.class);

	public static final String MODID = "umbral_skies";

	public UmbralSkies(IEventBus modEventBus) {
		UmbralBlocks.BLOCKS.register(modEventBus);
		UmbralBlocks.BLOCK_ENTITIES.register(modEventBus);
		UmbralItems.ITEMS.register(modEventBus);
		UmbralLootModifiers.LOOT_MODIFIERS.register(modEventBus);
		UmbralTab.TABS.register(modEventBus);

		modEventBus.addListener(UmbralData::generateData);
		modEventBus.addListener(this::setup);
	}

	public void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AccessoriesAPI.registerAccessory(UmbralItems.NAGA_GLOVES.get(), UmbralItems.NAGA_GLOVES.get());
			AccessoriesAPI.registerAccessory(UmbralItems.IRONWOOD_GLOVES.get(), UmbralItems.IRONWOOD_GLOVES.get());
			AccessoriesAPI.registerAccessory(UmbralItems.STEELEAF_GLOVES.get(), UmbralItems.STEELEAF_GLOVES.get());
			AccessoriesAPI.registerAccessory(UmbralItems.FIERY_GLOVES.get(), UmbralItems.FIERY_GLOVES.get());
			AccessoriesAPI.registerAccessory(UmbralItems.KNIGHTMETAL_GLOVES.get(), UmbralItems.KNIGHTMETAL_GLOVES.get());
			AccessoriesAPI.registerAccessory(UmbralItems.PHANTOM_GLOVES.get(), UmbralItems.PHANTOM_GLOVES.get());
			AccessoriesAPI.registerAccessory(UmbralItems.ARCTIC_GLOVES.get(), UmbralItems.ARCTIC_GLOVES.get());
			AccessoriesAPI.registerAccessory(UmbralItems.YETI_GLOVES.get(), UmbralItems.YETI_GLOVES.get());
		});
	}

	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name);
	}
}
