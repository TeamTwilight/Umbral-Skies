package us.drullk.umbralskies.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.registries.DeferredItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import twilightforest.block.entity.SkullChestBlockEntity;
import twilightforest.events.CharmEvents;
import twilightforest.init.TFItems;
import us.drullk.umbralskies.AccessoriesCompat;

import java.util.List;

@Mixin(CharmEvents.class)
public class CharmEventsMixin {
	@Inject(method = "applyCharm", at = @At(value = "RETURN"))
	private static void charmAccessories(DeferredItem<Item> charm, Inventory keptInventory, Player player, List<ItemStack> inventorySlots, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue() && (charm.is(TFItems.CHARM_OF_KEEPING_3) || charm.is(TFItems.CHARM_OF_KEEPING_2)))
			AccessoriesCompat.onCharmKeeping(player);
	}

	@Inject(method = "stockKeepsakeCasket", at = @At(value = "INVOKE", target = "Ltwilightforest/util/TFItemStackUtils;sortInvForCasket(Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/core/NonNullList;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
	private static void casketAccessories(Player player, CallbackInfo ci, boolean casketConsumed, Level level, BlockPos.MutableBlockPos pos, BlockPos immutablePos, FluidState fluidState, int damage, BlockState setState, SkullChestBlockEntity casket, String modifiedName, int casketCapacity, List<ItemStack> list) {
		AccessoriesCompat.onKeepsakeCasket(player, casketCapacity, list);
	}

	@Inject(method = "returnStoredItems", at = @At("TAIL"))
	private static void returnStoredItems(Player player, CallbackInfo ci) {
		AccessoriesCompat.returnStoredItems(player);
	}
}
