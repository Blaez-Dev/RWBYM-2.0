package be.bluexin.rwbym.weaponry;

import be.bluexin.rwbym.Init.RWBYItems;
import be.bluexin.rwbym.RWBYModels;
import com.google.common.collect.Sets;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Set;

/**
 * Part of rwbym
 *
 * @author Bluexin
 */
@MethodsReturnNonnullByDefault
public class RWBYSword extends ItemSword implements ICustomItem {
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);
    public final boolean isShield;
    public boolean neo = false;
    public final boolean canBlock;
    private final String data;
    //private final RecipeDTO[] recipes;
    private final String morph;
    private boolean fire;
    private boolean ice;
    private boolean velvet = false;
    private boolean crescentr = false;
    private boolean kkfire = false;
    private boolean kkice = false;
    private boolean kkwind = false;
    private boolean ohblade = false;
    private float damages = 0;
    private boolean magna = false;

    public RWBYSword(String name, int durability, float damage, int weapontype, String data, String morph,String ammo, boolean noCharge, float projectileSpeed, boolean shield, boolean canBlock, int recoilType, int bulletCount, int enchantmentglow,int soundeffect,  CreativeTabs creativetab) {
        super(EnumHelper.addToolMaterial(RWBYModels.MODID + ":" + name, 0, durability, 1.0F, damage, weapontype));
        this.setRegistryName(new ResourceLocation(RWBYModels.MODID, name));
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setCreativeTab(creativetab);
        this.data = data;
        if(name.contains("nora")) magna = true;
        if(name.contains("kkfire")) kkfire = true;
        if(name.contains("kkice")) kkice = true;
        if(name.contains("kkwind")) kkwind = true;
        //this.recipes = from.getRecipes();
        if(name.contains("crescent")) crescentr = true;
        if(name.contains("neoumb_closed")) neo = true;
        if(name.contains("neoumb_closed_blade")) neo = true;
        if(name.contains("neoumb_handle_blade")) neo = true;
        if(name.contains("gambol")|| name.contains("rvn")) {
            ohblade = true;
            this.damages = damage;
        }

        this.morph = morph;
        this.fire = fire;
        this.ice = ice;
        this.canBlock = canBlock;
        this.isShield = shield;

        if (this.neo) this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });

        if (this.isShield) this.addPropertyOverride(new ResourceLocation("offhand"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            @ParametersAreNonnullByDefault
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.getHeldItemOffhand() == stack ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        PotionEffect potioneffect = new PotionEffect(MobEffects.SLOWNESS, 200, 5, true, true);
       if (fire){target.setFire(10);}
       if (ice){
           target.addPotionEffect(potioneffect);
       }

        stack.damageItem(1, attacker);
        return true;
    }


    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        if(velvet){return true;}
        else
            return false;
    }

    @SuppressWarnings("Duplicates")
    public void onUpdate(ItemStack is, World world, Entity entity, int slotIn, boolean inHand) {

        if(entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (kkfire){if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == RWBYItems.korekosmoufire){
                ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                    is.setItemDamage(chest.getItemDamage());
                    chest.setItemDamage(is.getItemDamage());}
                else{is.damageItem(365, player);
            }}
        }
        if(entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (kkice){if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == RWBYItems.korekosmouwater){
                ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                is.setItemDamage(chest.getItemDamage());
                chest.setItemDamage(is.getItemDamage());}
            else{is.damageItem(365, player);
            }}
        }
        if(entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (kkwind){if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == RWBYItems.korekosmouwind){
                ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                is.setItemDamage(chest.getItemDamage());
                chest.setItemDamage(is.getItemDamage());}
            else{is.damageItem(365, player);
            }}
        }

        if(entity instanceof EntityPlayer && neo){
            final EntityPlayer player = (EntityPlayer)entity;
            if (!player.onGround && player.getItemInUseCount() > 1)
            {
                player.motionY += 0.05;
                player.fallDistance = 0;
                player.velocityChanged = true;
            }
        }

        if (!world.isRemote && this.data != null) {
            NBTTagCompound atag = is.getTagCompound();
            if (atag == null) atag = new NBTTagCompound();
            if (!atag.hasKey(KEY)) {
                atag.setBoolean(KEY, true);
                is.setTagCompound(atag);

                try {
                    is.setTagCompound(JsonToNBT.getTagFromJson(this.data));
                    is.getTagCompound().setBoolean(KEY, true);
                } catch (NBTException nbtexception) {
                    LogManager.getLogger(RWBYModels.MODID).error("Couldn't load data tag for " + this.getRegistryName());
                }
            }
        }

        if(!world.isRemote && this.data == null){{NBTTagCompound btag = is.getTagCompound();
            if (btag == null) btag = new NBTTagCompound();
            if (!btag.hasKey(KEY)) {
                btag.setBoolean(KEY, true);
                try {
                    is.setTagCompound(JsonToNBT.getTagFromJson("{AttributeModifiers:[{AttributeName:\"generic.attackSpeed\",Name:\"generic.attackSpeed\",Slot:\"mainhand\",Amount:0,Operation:0,UUIDMost:60527,UUIDLeast:119972}]}"));
                    //is.getTagCompound().merge(atag);
                } catch (NBTException nbtexception) {
                    LogManager.getLogger(RWBYModels.MODID).error("Couldn't load data tag for " + this.getRegistryName());
                }
            }}}
    }



    @ParametersAreNonnullByDefault
    @Override
    public ActionResult<ItemStack> onItemRightClick( World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack is = playerIn.getHeldItem(hand);

        if (!worldIn.isRemote && playerIn.isSneaking() && this.morph != null && playerIn.getHeldItemMainhand() == is) {
            is = new ItemStack(Item.getByNameOrId(this.morph), is.getCount(), is.getMetadata());
            return new ActionResult<>(EnumActionResult.SUCCESS, is);
        } else if (this.isShield && hand == EnumHand.OFF_HAND) {
            playerIn.setActiveHand(EnumHand.OFF_HAND);
            return new ActionResult<>(EnumActionResult.SUCCESS, is);
        }else if (canBlock && hand == EnumHand.MAIN_HAND) {
            playerIn.setActiveHand(EnumHand.MAIN_HAND);
            return new ActionResult<>(EnumActionResult.SUCCESS, is);
        }else return ActionResult.newResult(EnumActionResult.FAIL, is);}


    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        if (!worldIn.isRemote) {
	        if(ohblade && entityLiving instanceof EntityPlayer && entityLiving.getHeldItemOffhand() == stack) {
	        	Entity entity = this.findEntityOnPath(worldIn, entityLiving, entityLiving.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue());
	        	if (entity instanceof EntityLivingBase) {
	        		EntityLivingBase entitylivingbase = (EntityLivingBase) entity;
	                if (entitylivingbase != entityLiving && !entityLiving.isOnSameTeam(entitylivingbase)) {
	                    entitylivingbase.knockBack(entityLiving, 0.4F, (double) MathHelper.sin(entityLiving.rotationYaw * 0.017453292F), (double) (-MathHelper.cos(entityLiving.rotationYaw * 0.017453292F)));
	                    entitylivingbase.attackEntityFrom(DamageSource.GENERIC, damages + 4);
	                    stack.damageItem(1, entityLiving);
	                    entityLiving.world.playSound((EntityPlayer) null, entityLiving.posX, entityLiving.posY, entityLiving.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, entityLiving.getSoundCategory(), 1.0F, 1.0F);
	                }
	            }
	        }
        }
    }
    
    @Nullable
    protected Entity findEntityOnPath(World world, Entity entityIn, double range)
    {
        Entity entity = null;
        Vec3d start = entityIn.getPositionEyes(1);
        Vec3d look = entityIn.getLook(1);
        Vec3d end = start.addVector(look.x * range, look.y * range, look.z * range);
        RayTraceResult raytraceresult1 = world.rayTraceBlocks(start, end, false, true, false);

        if (raytraceresult1 != null)
        {
            end = new Vec3d(raytraceresult1.hitVec.x, raytraceresult1.hitVec.y, raytraceresult1.hitVec.z);
        }
        
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entityIn, new AxisAlignedBB(start.x, start.y, start.z, end.x, end.y, end.z));
        double d0 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = list.get(i);

            AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
            RayTraceResult raytraceresult2 = axisalignedbb.calculateIntercept(start, end);

            if (raytraceresult2 != null)
            {
                double d1 = start.squareDistanceTo(raytraceresult2.hitVec);

                if (d1 < d0 || d0 == 0.0D)
                {
                    entity = entity1;
                    d0 = d1;
                }
            }
        }

        return entity;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    public int getItemEnchantability()
    {
        return 0;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return  this.isShield ? 72000 : this.canBlock ? 72000: 0;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return this.isShield ? EnumAction.BLOCK : this.canBlock ? EnumAction.BLOCK  : EnumAction.NONE;
    }


    @Override
    public String toString() {
        return "RWBYSword{" + this.getRegistryName() + "}";
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (kkfire || kkwind || kkice){
            ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            chest.damageItem(1, player);
    }
        return super.onLeftClickEntity(stack, player, entity);
    }


    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment)
    {
        return enchantment.type.canEnchantItem(Items.DIAMOND_HOE);
    }


    @Override
    public boolean isRepairable() {
        if (kkice || kkfire || kkwind){return false;}
        else return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if (kkice || kkfire || kkwind){return false;}
        else return repair.getItem() == RWBYItems.scrap || super.getIsRepairable(toRepair, repair);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if(stack.getItem() == RWBYItems.lieutenant){return 10.0F;}
        else return super.getDestroySpeed(stack, state);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState blockState) {
        return super.getHarvestLevel(stack, toolClass, player, blockState);
    }
}
