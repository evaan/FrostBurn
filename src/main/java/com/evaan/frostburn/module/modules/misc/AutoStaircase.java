package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


/**
 * @Author majorsopa
 * https://github.com/majorsopa
 */
public class AutoStaircase extends Module {
    public AutoStaircase() {super("AutoStaircase", Category.MISC);}

    int toWalk = 0;

    Boolean crouching = true;

    @Override
    public void onUpdate() {
        PlayerEntity player = mc.player;
        World world = mc.world;


        // set vectors for directions
        // crouch
        Vec3d eastCrouch = new Vec3d(player.getX() + 1, player.getY() - 1.80278, player.getZ());
        // walk
        Vec3d eastWalk = new Vec3d(player.getX() + 1, player.getY() - 2.05913, player.getZ());

        // vector for below player
        Vec3d belowPlayer = new Vec3d(player.getX(), player.getY() - .01, player.getZ());
        BlockPos posBelowPlayer = new BlockPos(belowPlayer);


        if (player == null || world == null) return;

        try {
            for (int i = 0; i < 9; i++) {
                if (player.inventory.getStack(i).getItem() instanceof BlockItem) {
                    player.inventory.selectedSlot = i;
                }
            }
        } catch (Exception e) {
            System.out.println("no blocks");
        }

        Direction playerDirection = player.getHorizontalFacing();
        

        if (playerDirection == Direction.EAST) {
            float xPosToTenth;
            if (!crouching) {
                player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, eastWalk);
                xPosToTenth = (((float) (player.getX() - .1 / 1000000)) * 1000000);
            } else {
                player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, eastCrouch);
                xPosToTenth = (((float) (player.getX() / 1000000)) * 1000000);
            }



            // jump
            if (-.05 < (xPosToTenth * 10) % 10 && (xPosToTenth * 10) % 10 < .1) {
                mc.options.keyJump.setPressed(true);
            }


            // add to toWalk as to not walk every time
            toWalk++;

            // walk
            if (toWalk == 2) {
                mc.options.keyForward.setPressed(true);
                toWalk = 0;
                mc.options.keySneak.setPressed(true);
                crouching = true;
            } else if (toWalk == 1){
                mc.options.keyForward.setPressed(false);
                mc.options.keySneak.setPressed(false);
                crouching = false;
            } else {
                mc.options.keyForward.setPressed(false);
                crouching = false;
            }


            // place blocks
            mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(belowPlayer, Direction.DOWN, posBelowPlayer, false));
        }
    }
}