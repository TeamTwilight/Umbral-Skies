package us.drullk.umbralskies.item;

import io.wispforest.accessories.api.Accessory;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import org.jetbrains.annotations.Nullable;
import us.drullk.umbralskies.block.FloorAetherTrophyBlock;
import us.drullk.umbralskies.block.WallAetherTrophyBlock;

public class AetherTrophyItem extends StandingAndWallBlockItem implements Accessory {
	public AetherTrophyItem(FloorAetherTrophyBlock floorTrophyBlock, WallAetherTrophyBlock wallTrophyBlock, Properties properties) {
		super(floorTrophyBlock, wallTrophyBlock, properties, Direction.DOWN);
	}

	@Override
	public boolean canEquip(ItemStack stack, EquipmentSlot armorType, LivingEntity entity) {
		return armorType == EquipmentSlot.HEAD;
	}

	@Override
	public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EquipmentSlot.HEAD;
	}

}
