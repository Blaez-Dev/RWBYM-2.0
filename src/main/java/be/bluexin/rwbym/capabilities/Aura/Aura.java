package be.bluexin.rwbym.capabilities.Aura;

import be.bluexin.rwbym.RWBYModels;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import scala.tools.nsc.interactive.Main;

public class Aura implements IAura {
	
	/**Current Aura amount*/
	private float amount = 0;
	
	/**Maximum Aura*/
	private static final int MAX = 20;
	
	/**Amount to recharge*/
	private int recharge = 1;
	
	/**How many ticks until recharge*/
	private int rate = 10;
	
	private int delay = 0;
	
	@Override
	public void onUpdate(EntityPlayer player) {
		if (!player.getFoodStats().needFood()) {
			if (delay == 0) {
				if (player.world.getTotalWorldTime() % rate == 0) {
					if (amount < MAX) {
						player.getFoodStats().addExhaustion(recharge);
						amount += recharge;
					}
				}
			}
			else {
				delay--;
			}
		}
		//RWBYModels.LOGGER.info("{}, {}", amount, delay);
	}
	
	@Override
	public float useAura(EntityPlayer player, float usage) {
		float temp = amount - usage;
		amount = Math.max(temp, 0);
		return temp < 0 ? -temp : 0;
	}
	
	@Override
	public void delayRecharge(int ticks) {
		this.delay = ticks;
	}
	
	@Override
	public float getPercentage() {
		return amount / (float) MAX;
	}
	
	@Override
	public NBTBase serialize() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("amount", amount);
		nbt.setInteger("recharge", recharge);
		nbt.setInteger("rate", rate);
		return nbt;
	}

	@Override
	public void deserialize(NBTTagCompound nbt) {
		this.amount = nbt.getFloat("amount");
		this.recharge = nbt.getInteger("recharge");
		this.rate = nbt.getInteger("rate");
	}

}
