package be.bluexin.rwbym.weaponry.ammohit;

import java.util.List;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PotionAmmoHit implements IAmmoHit {
	
	private final List<PotionEffect> effects;
	private final Item type;

	public PotionAmmoHit(Item item, List<PotionEffect> effects) {
		this.effects = effects;
		this.type = item;
	}
	
	@Override
	public void applyBlock(World world, BlockPos pos) {
		if (!world.isRemote) {
			//EntityPotion potion = new EntityPotion(world, pos.getX(), pos.getY(), pos.getZ(), PotionUtils.appendEffects(new ItemStack(this.type), this.effects));
			//world.spawnEntity(potion);
			EntityAreaEffectCloud areaEffectCloud = new EntityAreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());
			areaEffectCloud.setRadius(2.0F);
			areaEffectCloud.setDuration(100);
			areaEffectCloud.setParticle(EnumParticleTypes.SPELL_INSTANT);
			for (PotionEffect p : this.effects) areaEffectCloud.addEffect(p);
			world.spawnEntity(areaEffectCloud);
		}
	}

	@Override
	public void applyEntity(EntityLivingBase living) {
		if (!living.world.isRemote) {
			//EntityPotion potion = new EntityPotion(living.world, living.posX, living.posY, living.posZ, PotionUtils.appendEffects(new ItemStack(this.type), this.effects));
			//living.world.spawnEntity(potion);
			EntityAreaEffectCloud areaEffectCloud = new EntityAreaEffectCloud(living.world, living.posX, living.posY, living.posZ);
			areaEffectCloud.setRadius(2.0F);
			areaEffectCloud.setDuration(100);
			areaEffectCloud.setParticle(EnumParticleTypes.SPELL_INSTANT);
			for (PotionEffect p : this.effects) areaEffectCloud.addEffect(p);
			living.world.spawnEntity(areaEffectCloud);

		}
	}

}


