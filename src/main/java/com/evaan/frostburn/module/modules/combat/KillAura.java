package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import com.google.common.collect.Streams;

import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;

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
    
    //todo rotate

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) return;
        if(mc.player.getAttackCooldownProgress(0) < 1) return;
        
        try {
	    	Entity entity;
	    	if(!allEntities.getValue()) {
	            entity = Streams.stream(mc.world.getEntities()).filter(e -> e instanceof PlayerEntity && mc.player.distanceTo(e) <= range.getValue() && e != mc.player).collect(Collectors.toList()).get(0);
	    	} else {
	    		entity = Streams.stream(mc.world.getEntities()).filter(e -> e instanceof Entity && mc.player.distanceTo(e) <= range.getValue() && e != mc.player).collect(Collectors.toList()).get(0);
	    	}
	        	
    		if(entity != null) {
    			if(!entity.isAttackable()) return;
    			
    			// We have a separate CrystalAura module
    			if(entity.getClass() == EndCrystalEntity.class) return;
    			
                if (switchItem.getValue()) {
                    for (int i = 0; i < 9; i++) {
                        if (mc.player.inventory.getStack(i).getItem() instanceof SwordItem)
                            mc.player.inventory.selectedSlot = i;
                    }
                }
                
                mc.interactionManager.attackEntity(mc.player, entity);
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        	
        } catch (Exception ignored) {}
    }
}
