package io.github.blaezdev.rwbym.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityBlakeIce extends EntityGolem {
    World world = null;
    private int counter;
    @Nullable
    public EntityBlakeIce(World var1) {
        super(var1);
        world = var1;
        this.setSize(1.5F, 1.5F);
    }


    protected void initEntityAI() {
        super.initEntityAI();
        //this.tasks.addTask(0, new EntityAISwimming(this));
        //this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        //this.tasks.addTask(8, new EntityAIWander(this, 0.6D));
        //this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        //this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        //this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityVindicator.class));
        //this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
        //this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, true, false, IMob.MOB_SELECTOR));
    }

    @Override
    public void onLivingUpdate() {
    	List<Entity> entitylist = world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(20D));
    	for (Entity entity : entitylist) {
    		if (entity instanceof EntityMob) {
    			EntityMob mob = (EntityMob) entity;
    			mob.setAttackTarget(this);
    		}
    	}
    	super.onLivingUpdate();
        counter += 1;
        if(counter >= 1200){
            this.setDead();
        }
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3499999940395355D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        this.world.setEntityState(this, (byte)4);
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));

        if (flag)
        {
            entityIn.motionY += 0.4000000059604645D;
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, 1.0F, 1.0F);
        return flag;
    }

    @Override
    public void onDeath(DamageSource cause) {
    	List<Entity> entitylist = world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(20D));
    	for (Entity entity : entitylist) {
    		if (entity instanceof EntityMob) {
    			EntityMob mob = (EntityMob) entity;
    			Entity target = mob.getAttackTarget();
    			if (target == this) {
    				mob.setAttackTarget(null);
    			}
    		}
    	}
        super.onDeath(cause);
        if (!this.world.isRemote)
        {
        EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(this.world, this.posX, this.posY, this.posZ);
        cloud.addEffect(new PotionEffect(MobEffects.SLOWNESS, 300, 55));
        cloud.setColor(0xccffff);
        cloud.setDuration(60);
        cloud.setRadius(3);
        cloud.setWaitTime(10);
        world.spawnEntity(cloud);
        }
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ILLAGER;
    }

    protected SoundEvent getAmbientSound() {
        return null;
    }

    protected SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_PLAYER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0F;
    }
}