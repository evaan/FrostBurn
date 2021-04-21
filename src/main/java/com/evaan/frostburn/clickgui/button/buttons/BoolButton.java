package com.evaan.frostburn.clickgui.button.buttons;

import com.evaan.frostburn.clickgui.button.ModuleButton;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.clickgui.button.SettingButton;
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
			textRenderer.draw(matrices, setting.getName(), (float) (getX() + 6), (float) (getY() + 4), new Color(30, 30, 216, 232).getRGB());
		}
		else {
			textRenderer.draw(matrices, setting.getName(), (float) (getX() + 6), (float) (getY() + 4), new Color(30, 30, 30).getRGB());
		}
	}

	@Override
	public Setting<?> getValue(){
		return setting;
	}

	@Override
	public void mouseDown(int mX, int mY, int mB) {
		if (!isHover(getX(), getY(), getW(), getH() - 1, mX, mY)) return;
		setting.setValue(!(boolean)setting.getValue());
	}
}
