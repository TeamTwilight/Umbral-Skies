package us.drullk.umbralskies.item;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.init.TFArmorMaterials;
import twilightforest.item.HollowLogItem;
import us.drullk.umbralskies.UmbralSkies;
import us.drullk.umbralskies.block.UmbralBlocks;

public class UmbralItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, UmbralSkies.MODID);

	public static final DeferredHolder<Item, GlovesItem> NAGA_GLOVES = ITEMS.register("naga_gloves", () -> new UmbralGloves(TFArmorMaterials.NAGA, 0.25, "naga", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item, GlovesItem> IRONWOOD_GLOVES = ITEMS.register("ironwood_gloves", () -> new UmbralGloves(TFArmorMaterials.IRONWOOD, 0.5, "ironwood", new Item.Properties().stacksTo(1)));
	public static final DeferredHolder<Item, GlovesItem> FIERY_GLOVES = ITEMS.register("fiery_gloves", () -> new UmbralGloves(TFArmorMaterials.FIERY, 1, "fiery", new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item, GlovesItem> STEELEAF_GLOVES = ITEMS.register("steeleaf_gloves", () -> new UmbralGloves(TFArmorMaterials.STEELEAF, 0.75, "steeleaf", new Item.Properties().stacksTo(1)));
	public static final DeferredHolder<Item, GlovesItem> KNIGHTMETAL_GLOVES = ITEMS.register("knightmetal_gloves", () -> new UmbralGloves(TFArmorMaterials.KNIGHTMETAL, 1, "knightmetal", new Item.Properties().stacksTo(1)));
	public static final DeferredHolder<Item, GlovesItem> PHANTOM_GLOVES = ITEMS.register("phantom_gloves", () -> new PhantomGloves(TFArmorMaterials.PHANTOM, 1, "phantom", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item, GlovesItem> ARCTIC_GLOVES = ITEMS.register("arctic_gloves", () -> new UmbralGloves(TFArmorMaterials.ARCTIC, 0.25, "arctic", new Item.Properties().stacksTo(1)));
	public static final DeferredHolder<Item, GlovesItem> YETI_GLOVES = ITEMS.register("yeti_gloves", () -> new UmbralGloves(TFArmorMaterials.YETI, 0.75, "yeti", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

	public static final DeferredHolder<Item, BlockItem> SKYROOT_BANISTER = ITEMS.register("skyroot_banister", () -> new BlockItem(UmbralBlocks.SKYROOT_BANISTER.get(), new Item.Properties()));
	public static final DeferredHolder<Item, HollowLogItem> HOLLOW_SKYROOT_LOG = ITEMS.register("hollow_skyroot_log", () -> new HollowLogItem(UmbralBlocks.HOLLOW_SKYROOT_LOG_HORIZONTAL, UmbralBlocks.HOLLOW_SKYROOT_LOG_VERTICAL, UmbralBlocks.HOLLOW_SKYROOT_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredHolder<Item, HollowLogItem> HOLLOW_GOLDEN_OAK_LOG = ITEMS.register("hollow_golden_oak_log", () -> new HollowLogItem(UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_HORIZONTAL, UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_VERTICAL, UmbralBlocks.HOLLOW_GOLDEN_OAK_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredHolder<Item, AetherTrophyItem> SLIDER_TROPHY = ITEMS.register("slider_trophy", () -> new AetherTrophyItem(UmbralBlocks.SLIDER_TROPHY_BLOCK.get(), UmbralBlocks.SLIDER_WALL_TROPHY_BLOCK.get(), new Item.Properties()));
	public static final DeferredHolder<Item, AetherTrophyItem> VALKYRIE_QUEEN_TROPHY = ITEMS.register("valkyrie_queen_trophy", () -> new AetherTrophyItem(UmbralBlocks.VALKYRIE_QUEEN_TROPHY_BLOCK.get(), UmbralBlocks.VALKYRIE_WALL_QUEEN_TROPHY_BLOCK.get(), new Item.Properties()));
	public static final DeferredHolder<Item, AetherTrophyItem> SUN_SPIRIT_TROPHY = ITEMS.register("sun_spirit_trophy", () -> new AetherTrophyItem(UmbralBlocks.SUN_SPIRIT_TROPHY_BLOCK.get(), UmbralBlocks.SUN_SPIRIT_WALL_TROPHY_BLOCK.get(), new Item.Properties()));
}
