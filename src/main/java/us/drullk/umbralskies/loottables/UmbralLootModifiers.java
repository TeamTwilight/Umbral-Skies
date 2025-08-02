package us.drullk.umbralskies.loottables;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import us.drullk.umbralskies.UmbralSkies;

public class UmbralLootModifiers {
	public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, UmbralSkies.MODID);

	public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ItemAdditionModifier>> LOOT_ADDITION = LOOT_MODIFIERS.register("item_addition", () -> ItemAdditionModifier.CODEC);
}
