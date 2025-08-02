package us.drullk.umbralskies.item;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import us.drullk.umbralskies.UmbralSkies;

public class UmbralGloves extends GlovesItem {

	public UmbralGloves(Holder<ArmorMaterial> armorMaterial, double punchDamage, String glovesName, Properties properties) {
		super(armorMaterial, punchDamage, glovesName, SoundEvents.ARMOR_EQUIP_GENERIC, properties.durability(armorMaterial.value().getDefense(ArmorItem.Type.BOOTS)));
	}

	@Override
	public void setRenderTexture(String modId, String registryName) {
		this.GLOVES_TEXTURE = UmbralSkies.prefix("textures/models/gloves/" + registryName + ".png");
	}
}
