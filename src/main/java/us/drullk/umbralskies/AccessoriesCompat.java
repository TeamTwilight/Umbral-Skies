package us.drullk.umbralskies;

import com.google.common.base.Suppliers;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.AccessoriesContainer;
import io.wispforest.accessories.endec.NbtMapCarrier;
import io.wispforest.accessories.impl.AccessoriesHolderImpl;
import io.wispforest.accessories.impl.ExpandedSimpleContainer;
import io.wispforest.endec.SerializationAttribute;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import twilightforest.events.CharmEvents;

import io.wispforest.endec.SerializationContext;
import io.wispforest.owo.serialization.RegistriesAttribute;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class AccessoriesCompat {
	public static final String ACCESSORIES = "UmbralSkiesCharmAccessories";
	public static String FIND_CLASS = "io.wispforest.accessories.impl.AccessoriesHolderImpl$EntityAttribute";

	public static Supplier<Constructor<?>> ENTITY_ATTRIBUTE_CONSTRUCTOR = Suppliers.memoize(() -> {
		try {
			Constructor<?> constructor = Class.forName(FIND_CLASS).getDeclaredConstructor(LivingEntity.class);
			constructor.setAccessible(true);
			return constructor;
		} catch (NoSuchMethodException | ClassNotFoundException e) {
			UmbralSkies.LOGGER.error("Errored trying to instantiate {}", FIND_CLASS, e);
			throw new RuntimeException(e);
		}
	});

	public static void onCharmKeeping(Player player) {
		if (!(AccessoriesCapability.get(player).getHolder() instanceof AccessoriesHolderImpl accessoriesHolder))
			return;

		NbtMapCarrier carrier = NbtMapCarrier.of();
		accessoriesHolder.write(carrier, SerializationContext.attributes(RegistriesAttribute.of(player.level().registryAccess())));
		CharmEvents.getPlayerData(player).put(ACCESSORIES, carrier.compoundTag());
		accessoriesHolder.getSlotContainers().forEach((name, container) -> container.getAccessories().removeAllItems());
	}

	public static void onKeepsakeCasket(Player player, int casketCapacity, List<ItemStack> list) {
		if (!(AccessoriesCapability.get(player).getHolder() instanceof AccessoriesHolderImpl accessoriesHolder))
			return;

		Set<Map.Entry<String, AccessoriesContainer>> entries = accessoriesHolder.getSlotContainers().entrySet();

		// Preserve regular accessories before cosmetics. There's unfortunately only so many slots in the casket!
		for (Map.Entry<String, AccessoriesContainer> entry : entries) {
			moveItemsToCasket(casketCapacity, list, entry.getValue().getAccessories());
		}
		for (Map.Entry<String, AccessoriesContainer> entry : entries) {
			moveItemsToCasket(casketCapacity, list, entry.getValue().getCosmeticAccessories());
		}
	}

	private static void moveItemsToCasket(int casketCapacity, List<ItemStack> casketInventory, ExpandedSimpleContainer accessories) {
		for (int containerIndex = 0; containerIndex < accessories.getContainerSize() && casketInventory.size() < casketCapacity - 1; containerIndex++) {
			ItemStack item = accessories.getItem(containerIndex);

			if (item.isEmpty()) continue;

			addItemToList(item, casketInventory);

			accessories.setItem(containerIndex, item);
		}
	}

	public static void addItemToList(ItemStack insert, List<ItemStack> casketInventory) {
		for (int slotIndex = 0; slotIndex < casketInventory.size(); slotIndex++) {
			ItemStack inventoryStack = casketInventory.get(slotIndex);

			if (inventoryStack.isEmpty()) {
				casketInventory.set(slotIndex, insert.copy());
				insert.setCount(0);
				return;
			}

			if (!ItemStack.isSameItemSameComponents(insert, inventoryStack))
				continue;

			int maxStackSize = inventoryStack.getMaxStackSize();
			if (maxStackSize >= insert.getCount() + inventoryStack.getCount()) {
				inventoryStack.setCount(inventoryStack.getCount() + insert.getCount());
				insert.setCount(0);
				return;
			}

			int canInsert = Math.min(maxStackSize - inventoryStack.getCount(), insert.getCount());
			inventoryStack.setCount(inventoryStack.getCount() + canInsert);
			insert.shrink(canInsert);
			if (insert.isEmpty())
				return;
		}
	}

	public static void returnStoredItems(Player player) {
		if (!(AccessoriesCapability.get(player).getHolder() instanceof AccessoriesHolderImpl accessoriesHolder && CharmEvents.getPlayerData(player).get(ACCESSORIES) instanceof CompoundTag tag))
			return;

		accessoriesHolder.read(player, new NbtMapCarrier(tag), SerializationContext.attributes(nastyReflection(player), RegistriesAttribute.of(player.level().registryAccess())));
		CharmEvents.getPlayerData(player).remove(ACCESSORIES);
	}

	private static SerializationAttribute.Instance nastyReflection(LivingEntity player) {
		try {
			return (SerializationAttribute.Instance) ENTITY_ATTRIBUTE_CONSTRUCTOR.get().newInstance(player);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			UmbralSkies.LOGGER.error("Error while trying to instantiate {}", FIND_CLASS, e);
			throw new RuntimeException(e);
		}
	}
}
