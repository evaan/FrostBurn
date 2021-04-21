package com.evaan.frostburn.module.modules.render;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.Setting;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Calendar;

//fix your shit ass 1.12 skit idiot
public class HUD extends Module {
    public HUD() {super("HUD", Category.RENDER); setDrawn(false);}

    Setting<Boolean> watermark = register(new Setting<>("Watermark", this, true));
    Setting<Boolean> greeter = register(new Setting<>("Greeter",this, true));
    Setting<Boolean> arrayList = register(new Setting<>("ArrayList",this, true));
    Setting<Boolean> coords = register(new Setting<>("Coordinates",this, true));
    Setting<Integer> r = register(new Setting<>("Red", this, 255, 0, 255));
    Setting<Integer> g = register(new Setting<>("Green", this, 0, 0, 255));
    Setting<Integer> b = register(new Setting<>("Blue", this, 0, 0, 255));
    Setting<Boolean> rainbow = register(new Setting<>("Rainbow", this, true));

    public int rgb;
    public int a;
    public int r1;
    public int g1;
    public int b1;
    float hue = 0.01f;

    public int y;

    public void updateRainbow() {
        rgb = Color.HSBtoRGB(hue, 1, 1);
        a = (rgb >>> 24) & 0xFF;
        r1 = (rgb >>> 16) & 0xFF;
        g1 = (rgb >>> 8) & 0xFF;
        b1 = rgb & 0xFF;
        hue += 0.0005;
        if (hue > 1) hue -= 1;
    }

    @Override
    public void onRender1(MatrixStack matrices) {
        rgb = (rainbow.getValue() ? rgb : new Color(r.getValue(), g.getValue(), b.getValue()).getRGB());
        y=2;
        if (watermark.getValue()) {
            mc.textRenderer.drawWithShadow(matrices, "FrostBurn 1.0", 2, 2, rgb);
            y += 10;
        }
        if (greeter.getValue()) {
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hour >= 0 && hour < 12) mc.textRenderer.drawWithShadow(matrices, "Good morning " + mc.player.getName().asString() + " :^)", (mc.getWindow().getWidth()/2)-(mc.textRenderer.getWidth("Good morning " + mc.player.getName() + ":^)")/2),2, rgb);
            else if (hour >= 12 && hour < 16) mc.textRenderer.drawWithShadow(matrices,"Good afternoon " + mc.player.getName().asString() + " :^)", (mc.getWindow().getWidth()/2)-(mc.textRenderer.getWidth("Good afternoon " + mc.player.getName() + ":^)")/2),2, rgb);
            else if (hour >= 16 && hour < 24) mc.textRenderer.drawWithShadow(matrices, "Good evening " + mc.player.getName().asString() + " :^)", (mc.getWindow().getWidth()/2)-(mc.textRenderer.getWidth("Good evening " + mc.player.getName() + ":^)")/2),2, rgb);
        }
        if (arrayList.getValue()) {
            ModuleManager.modules.stream().filter(Module::isEnabled).filter(Module::isDrawn).forEach(module -> {
                updateRainbow();
                mc.textRenderer.drawWithShadow(matrices, module.getName() + " " + module.getHudInfo(), 2, y, rgb);
                y+=10;
            });
        }
        if (coords.getValue()) {
            updateRainbow();
            if (mc.player == null) return;
            DecimalFormat format = new DecimalFormat("0.#");
            if (mc.world.getRegistryKey().getValue().getPath().equalsIgnoreCase("the_nether")) mc.textRenderer.drawWithShadow(matrices, format.format(mc.player.getX()) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getY()) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getZ()) + Formatting.WHITE + " [" + Formatting.RESET + format.format(mc.player.getX()/8) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getY()) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getZ()/8) + Formatting.WHITE + "]", 2, mc.getWindow().getHeight()-mc.textRenderer.fontHeight-2, rgb);
            else if (mc.world.getRegistryKey().getValue().getPath().equalsIgnoreCase("overworld")) mc.textRenderer.drawWithShadow(matrices, format.format(mc.player.getX()) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getY()) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getZ()) + Formatting.WHITE + " [" + Formatting.RESET + format.format(mc.player.getX()*8) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getY()) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getZ()*8) + Formatting.WHITE + "]", 2, mc.getWindow().getHeight()-mc.textRenderer.fontHeight-2, rgb);
            else mc.textRenderer.drawWithShadow(matrices, format.format(mc.player.getX()) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getY()) + Formatting.WHITE + ", " + Formatting.RESET + format.format(mc.player.getZ()), 2, mc.getWindow().getHeight()-mc.textRenderer.fontHeight-2, rgb);
        }
    }
}