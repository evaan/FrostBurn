package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.Iterator;

public class Nuker extends Module {
    public Nuker() {super("Nuker", Category.MISC);}

    Setting<Boolean> faceBlock = register(
            new Setting(
                    "FaceBlock",
                    this,
                    true
            )
    );
    Setting<Boolean> swing = register(
            new Setting(
                    "Swing",
                    this,
                    true
            )
    );
    Setting<Float> range = register(
            new Setting(
                    "Range",
                    this,
                    3f,
                    0f,
                    4f
            )
    );
    Setting<Float> blocksToMine = register(
            new Setting(
                    "BlocksToMine",
                    this,
                    1f,
                    1f,
                    10f
            )
    );


    ArrayList<BlockPos> blocks = new ArrayList<>();

    @Override
    public void onUpdate() {
        assert mc.player != null;
        assert mc.world != null;

        int varRange = range.getValue().intValue();
        int xPos = (int) mc.player.getX();
        int yPos = (int) mc.player.getY();
        int zPos = (int) mc.player.getZ();

        int positiveSideX = xPos + varRange;
        int positiveSideY = yPos + varRange;
        int positiveSideZ = zPos + varRange;

        int negativeSideX = xPos - varRange;
        int negativeSideZ = zPos - varRange;

        for (int y = yPos; y <= positiveSideY; y++) {
            for (int x = negativeSideX; x <= positiveSideX; x++) {
                for (int z = negativeSideZ; z <= positiveSideZ; z++) {
                    BlockPos bp = new BlockPos(x, y, z);
                    if (mc.world.getBlockState(bp).getBlock() != Blocks.AIR){
                        blocks.add(bp);
                    }
                }
            }
        }


        try {
            if (blocks.size() > 0) {
                if (faceBlock.getValue()) {
                    mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, Vec3d.of(blocks.get(0)));
                }

                Iterator<BlockPos> blocksIter = blocks.iterator();
                for (int j = 0; j < blocks.size(); j++) {
                    if (j < blocksToMine.getValue() && blocksIter.hasNext()) {
                        breakBlock(blocksIter.next());
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception ignored) {}

        blocks.clear();
    }

    private void breakBlock(BlockPos blockPos) {
        if (mc.interactionManager != null) {
            mc.interactionManager.updateBlockBreakingProgress(blockPos, Direction.UP);

            assert mc.player != null;
            if (swing.getValue()) {
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }
}
