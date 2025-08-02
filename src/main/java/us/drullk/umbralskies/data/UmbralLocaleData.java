package us.drullk.umbralskies.data;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.LanguageProvider;
import us.drullk.umbralskies.UmbralTab;
import us.drullk.umbralskies.block.UmbralBlocks;
import us.drullk.umbralskies.item.UmbralItems;
import us.drullk.umbralskies.UmbralSkies;

import java.util.function.Supplier;

public class UmbralLocaleData extends LanguageProvider {
	public UmbralLocaleData(PackOutput output) {
		super(output, UmbralSkies.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.addBlock(UmbralBlocks.SKYROOT_BANISTER, "Skyroot Banister");

		this.addBlock(UmbralBlocks.HOLLOW_SKYROOT_LOG_HORIZONTAL, "Hollow Skyroot Log");
		this.addBlock(UmbralBlocks.HOLLOW_SKYROOT_LOG_VERTICAL, "Hollow Skyroot Log");
		this.addBlock(UmbralBlocks.HOLLOW_SKYROOT_LOG_CLIMBABLE, "Hollow Skyroot Log");

		this.addBlock(UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_HORIZONTAL, "Hollow Golden Oak Log");
		this.addBlock(UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_VERTICAL, "Hollow Golden Oak Log");
		this.addBlock(UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_CLIMBABLE, "Hollow Golden Oak Log");

		this.addItem(UmbralItems.NAGA_GLOVES, "Naga Scale Gloves");
		this.addItem(UmbralItems.IRONWOOD_GLOVES, "Ironwood Gloves");
		this.addItem(UmbralItems.FIERY_GLOVES, "Fiery Gloves");
		this.addItem(UmbralItems.STEELEAF_GLOVES, "Steeleaf Gloves");
		this.addItem(UmbralItems.KNIGHTMETAL_GLOVES, "Knightmetal Gauntlets");
		this.addItem(UmbralItems.PHANTOM_GLOVES, "Phantom Gauntlets");
		this.addItem(UmbralItems.ARCTIC_GLOVES, "Arctic Mittens");
		this.addItem(UmbralItems.YETI_GLOVES, "Yeti Gloves");

		this.addItem(UmbralItems.SLIDER_TROPHY, "Slider Trophy");
		this.addItem(UmbralItems.VALKYRIE_QUEEN_TROPHY, "Valkyrie Queen Trophy");
		this.addItem(UmbralItems.SUN_SPIRIT_TROPHY, "Sun Spirit Trophy");

		this.makeAetherLore(UmbralBlocks.SKYROOT_BANISTER, "A banister like those found from the Twilight Forest, however made of Skyroot wood.");

		this.makeAetherLore(UmbralItems.NAGA_GLOVES, "An accessory for Naga armor set.");
		this.makeAetherLore(UmbralItems.IRONWOOD_GLOVES, "An accessory for Ironwood armor set.");
		this.makeAetherLore(UmbralItems.FIERY_GLOVES, "An accessory for Fiery armor set.");
		this.makeAetherLore(UmbralItems.STEELEAF_GLOVES, "An accessory for Steeleaf armor set.");
		this.makeAetherLore(UmbralItems.KNIGHTMETAL_GLOVES, "An accessory for Knightmetal armor set.");
		this.makeAetherLore(UmbralItems.PHANTOM_GLOVES, "An accessory for Phantom armor set.");
		this.makeAetherLore(UmbralItems.ARCTIC_GLOVES, "An accessory for Arctic armor set.");
		this.makeAetherLore(UmbralItems.YETI_GLOVES, "An accessory for Yeti armor set.");

		this.makeAetherLore(UmbralItems.SLIDER_TROPHY, "The trophy from the Slider, who sleeps inside the Bronze Dungeon.");
		this.makeAetherLore(UmbralItems.VALKYRIE_QUEEN_TROPHY, "The trophy from the Valkyrie Queen, who rules the Silver Dungeon.");
		this.makeAetherLore(UmbralItems.SUN_SPIRIT_TROPHY, "The trophy from the Sun Spirit, who resides within the Gold Dungeon.");

		this.add(UmbralTab.TAB_LOCALE, "Umbral Skies");
	}

	private void makeAetherLore(Supplier<? extends ItemLike> item, String text) {
		this.add("lore." + item.get().asItem().getDescriptionId(), text);
	}
}
