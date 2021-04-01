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

public class Nuker extends Module {
    public Nuker() {super("Nuker", Category.MISC);}

    Setting<Boolean> faceBlock = register(
            new Setting(
                    "FaceBlock",
                    this,
                    true
            )
    );
    Setting<Float> range = register(
            new Setting(
                    "Range",
                    this,
                    3f,
                    0,
                    4
            )
    );
    Setting<Float> blocksToMine = register(
            new Setting(
                    "BlocksToMine",
                    this,
                    1f,
                    1,
                    10
            )
    );


    ArrayList<BlockPos> blocks = new ArrayList<>();

    @Override
    public void onUpdate() {
        assert mc.player != null;
        assert mc.world != null;

        int positiveSideX = ((int) mc.player.getX()) + (range.getValue().intValue());
        int positiveSideY = ((int) mc.player.getY()) + (range.getValue().intValue());
        int positiveSideZ = ((int) mc.player.getZ()) + (range.getValue().intValue());

        int negativeSideX = ((int) mc.player.getX()) - (range.getValue().intValue());
        int negativeSideY = (int) mc.player.getY();
        int negativeSideZ = ((int) mc.player.getZ()) - (range.getValue().intValue());

        for (int y = negativeSideY; y <= positiveSideY; y++) {
            for (int x = negativeSideX; x <= positiveSideX; x++) {
                for (int z = negativeSideZ; z <= positiveSideZ; z++) {
                    blocks.add(new BlockPos(x, y, z));
                }
            }
        }

        int length = blocks.size() - 1;
        // clear air from the block list
        for (int j = length; j >= 0; j--) {
            if (mc.world.getBlockState(blocks.get(j)).getBlock() == Blocks.AIR) {
                blocks.remove(j);
            }
        }

        if (faceBlock.getValue()) {
            try {
                mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, Vec3d.of(blocks.get(0)));
            } catch (Exception ignored) {}
        }

        for (int i = 0; i <= blocksToMine.getValue().intValue() - 1; i++) {
            try {
                breakBlock(blocks.get(i));
            } catch (Exception ignored) {
                break;
            }
        }

        blocks.clear();
    }

    private void breakBlock(BlockPos blockPos) {
        if (mc.interactionManager != null) {
            mc.interactionManager.updateBlockBreakingProgress(blockPos, Direction.UP);

            assert mc.player != null;
            mc.player.swingHand(Hand.MAIN_HAND);

        }
    }
}
