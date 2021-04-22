package com.evaan.frostburn.clickgui.button.buttons;

import com.evaan.frostburn.clickgui.button.ModuleButton;
import com.evaan.frostburn.clickgui.button.SettingButton;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.modules.render.ClickGuiMod;
import com.evaan.frostburn.util.Setting;
import com.evaan.frostburn.util.Wrapper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

/**
 * @author Gopro336
 */
public class ModeButton extends SettingButton implements Wrapper
{
	private final Setting setting;

	public ModeButton(ModuleButton parent, Module module, Setting<?> setting, double X, double Y, double W, double H)
	{
		super(parent, module, X, Y, W, H);
		this.setting = setting;
	}

	@Override
	public void render(MatrixStack matrices, int mX, int mY)
	{
		drawButton(matrices, mX, mY);

		textRenderer.draw(matrices, setting.getName(), (float) (getX() + 10), (float) (getY() + 4), new Color(ClickGuiMod.clickGuiMod.textR.getValue(), ClickGuiMod.clickGuiMod.textG.getValue(), ClickGuiMod.clickGuiMod.textB.getValue(), ClickGuiMod.clickGuiMod.textA.getValue()).getRGB());
		textRenderer.draw(matrices, setting.getValue().toString(), (float) ((getX() + getW() - 6) - textRenderer.getWidth(setting.getValue().toString())), (float) (getY() + 4), new Color(ClickGuiMod.clickGuiMod.textR.getValue(), ClickGuiMod.clickGuiMod.textG.getValue(), ClickGuiMod.clickGuiMod.textB.getValue(), ClickGuiMod.clickGuiMod.textA.getValue()).getRGB());
	}

	@Override
	public void mouseDown(int mX, int mY, int mB)
	{
		super.mouseClicked(mX, mY, mB);
		if (this.isHovering(mX, mY)) {
			String s = (setting.getValue() instanceof String ? (String) setting.getValue() : setting.getValue().toString());
			if (mB == 0) {
				try {
					if (!setting.getCorrectString(s).equalsIgnoreCase(setting.getOptions().get(setting.getOptions().size() - 1).toString())) {
						setting.setValue(setting.getOptions().get(setting.getOptions().indexOf(setting.getCorrectString(s)) + 1));
					} else {
						setting.setValue(setting.getOptions().get(0));
					}
				} catch (Exception e) {
					System.err.println("Mode Button Error");
					e.printStackTrace();
					setting.setValue(setting.getOptions().get(0));
				}
			}
			/*else if (mB == 1) {
			add decrement?
			}*/
		}
	}

	@Override
	public Setting<?> getValue(){
		return setting;
	}

}
