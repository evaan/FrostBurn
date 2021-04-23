package com.evaan.frostburn.clickgui.button.buttons;

import com.evaan.frostburn.clickgui.ClickGui;
import com.evaan.frostburn.clickgui.button.ModuleButton;
import com.evaan.frostburn.clickgui.button.SettingButton;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.modules.render.ClickGuiMod;
import com.evaan.frostburn.util.Setting;
import com.evaan.frostburn.util.Wrapper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Pair;

import java.awt.*;

public class BindButton extends SettingButton implements Wrapper
{
	ModuleButton parent;

	public BindButton(ModuleButton parent, Module module, double X, double Y, double W, double H) {
		super(parent, module, X, Y, W, H);
		this.parent = parent;
	}

	@Override
	public void render(MatrixStack matrices, int mX, int mY) {

		drawButton(matrices, mX, mY);
		textRenderer.draw(matrices, "Bind: " + InputUtil.fromKeyCode(getModule().getBind(), -1).getLocalizedText().asString() + (ClickGui.isBinding.getLeft() && ClickGui.isBinding.getRight().equals(parent) ? "..." : ""), (float) (getX() + 10), (float) (getY() + 4), new Color(new Color(ClickGuiMod.clickGuiMod.textR.getValue(), ClickGuiMod.clickGuiMod.textG.getValue(), ClickGuiMod.clickGuiMod.textB.getValue(), ClickGuiMod.clickGuiMod.textA.getValue()).getRGB()).getRGB());	}

	@Override
	public Setting<?> getValue(){
		return setting;
	}

	@Override
	public void mouseDown(int mX, int mY, int mB) {
		if (isHover(getX(), getY(), getW(), getH() - 1, mX, mY) && mB == 0) ClickGui.isBinding = new Pair<Boolean, ModuleButton>(true, parent);

	}
}
