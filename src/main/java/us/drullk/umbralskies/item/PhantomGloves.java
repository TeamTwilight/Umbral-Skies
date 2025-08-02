package us.drullk.umbralskies.item;

import io.wispforest.accessories.api.DropRule;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class PhantomGloves extends UmbralGloves {

	private static final MutableComponent TOOLTIP = Component.translatable("item.twilightforest.phantom_armor.desc").withStyle(ChatFormatting.GRAY);

	public PhantomGloves(Holder<ArmorMaterial> armorMaterial, double punchDamage, String glovesName, Properties properties) {
		super(armorMaterial, punchDamage, glovesName, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltips, TooltipFlag isAdvanced) {
		tooltips.add(TOOLTIP);
	}

	@Override
	public DropRule getDropRule(ItemStack stack, SlotReference reference, DamageSource source) {
		return DropRule.KEEP;
	}
}
