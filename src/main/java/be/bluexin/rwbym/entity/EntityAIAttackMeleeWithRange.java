package be.bluexin.rwbym.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIAttackMeleeWithRange extends EntityAIAttackMelee {
	
	private float range;

	public EntityAIAttackMeleeWithRange(EntityCreature creature, double speedIn, boolean useLongMemory, float range) {
		super(creature, speedIn, useLongMemory);
		this.range = range;
	}

	@Override
	protected double getAttackReachSqr(EntityLivingBase attackTarget) {
		return this.attacker.width + attackTarget.width + this.range;
	}
	
}
