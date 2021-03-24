package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import com.google.common.collect.Streams;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class KillAura extends Module {
    public KillAura() {super("KillAura", Category.COMBAT);}
    
    Setting<Float> range = register(new Setting("Range", this, 4.0f, 0.1f, 6.0f));
    Setting<Boolean> switchItem = register(new Setting("Switch", this, true));
    Setting<Boolean> allEntities = register(new Setting("AllEntities", this, true));
    Setting<Boolean> multiAura = register(new Setting("Multi", this, true));
    
    //todo rotate

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) return;
        if(mc.player.getAttackCooldownProgress(0) < 1) return;
        
        try {
	    	List<Entity> filtered;
	    	if(!allEntities.getValue()) {
	    		filtered = Streams.stream(mc.world.getEntities()).filter(e -> e instanceof PlayerEntity && mc.player.distanceTo(e) <= range.getValue() && e != mc.player).collect(Collectors.toList());
	    	} else {
	    		filtered = Streams.stream(mc.world.getEntities()).filter(e -> e instanceof Entity && mc.player.distanceTo(e) <= range.getValue() && e != mc.player).collect(Collectors.toList());
	    	}
	        
	    	for(Entity entity : filtered) {
	    		if(entity != null) {
	    			// Don't attack dead/non living entities, ones we can't attack, and end crystals
	    			if(entity.isLiving() && entity.isAttackable() && !(entity.getClass() == EndCrystalEntity.class)) {
		                if (switchItem.getValue()) {
		                    for (int i = 0; i < 9; i++) {
		                        if (mc.player.inventory.getStack(i).getItem() instanceof SwordItem)
		                            mc.player.inventory.selectedSlot = i;
		                    }
		                }
		                
		                mc.interactionManager.attackEntity(mc.player, entity);
		                mc.player.swingHand(Hand.MAIN_HAND);
		                
		                if(!multiAura.getValue()) break;
 	    			}
	            }
	    	}
        	
        } catch (Exception ignored) {}
    }
}
