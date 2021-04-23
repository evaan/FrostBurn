package com.evaan.frostburn.clickgui.button.buttons;

import com.evaan.frostburn.clickgui.button.ModuleButton;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.clickgui.button.SettingButton;
import com.evaan.frostburn.module.modules.render.ClickGuiMod;
import com.evaan.frostburn.util.Setting;
import com.evaan.frostburn.util.Wrapper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class BoolButton extends SettingButton implements Wrapper
{
	private final Setting setting;

	public BoolButton(ModuleButton parent, Module module, Setting<?> setting, double X, double Y, double W, double H) {
		super(parent, module, X, Y, W, H);
		this.setting = setting;
	}

	@Override
	public void render(MatrixStack matrices, int mX, int mY) {

		drawButton(matrices, mX, mY);

		if ((boolean)setting.getValue()) {
			textRenderer.draw(matrices, setting.getName(), (float) (getX() + 10), (float) (getY() + 4), new Color(new Color(ClickGuiMod.clickGuiMod.textAltR.getValue(), ClickGuiMod.clickGuiMod.textAltG.getValue(), ClickGuiMod.clickGuiMod.textAltB.getValue(), ClickGuiMod.clickGuiMod.textAltA.getValue()).getRGB()).getRGB());
		}
		else {
			textRenderer.draw(matrices, setting.getName(), (float) (getX() + 10), (float) (getY() + 4), new Color(ClickGuiMod.clickGuiMod.textR.getValue(), ClickGuiMod.clickGuiMod.textG.getValue(), ClickGuiMod.clickGuiMod.textB.getValue(), ClickGuiMod.clickGuiMod.textA.getValue()).getRGB());
		}
	}

	@Override
	public Setting<?> getValue(){
		return setting;
	}

	@Override
	public void mouseDown(int mX, int mY, int mB) {
		if (isHover(getX(), getY(), getW(), getH() - 1, mX, mY) && mB == 0) setting.setValue(!(boolean)setting.getValue());
	}
}
