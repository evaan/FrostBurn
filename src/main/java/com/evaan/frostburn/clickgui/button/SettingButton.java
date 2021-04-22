package com.evaan.frostburn.clickgui.button;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.modules.render.ClickGuiMod;
import com.evaan.frostburn.util.Setting;
import com.evaan.frostburn.util.Wrapper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

/**
 * @author Gopro336
 */
public class SettingButton implements Wrapper
{
	public Setting setting;
	private boolean state;
	private final double W;
	private final double H;
	private Module module;
	private ModuleButton parentMB;
	private double X;
	private double Y;

	public SettingButton(ModuleButton parent, Module module, double x, double y, double w, double h) {
		this.module = module;
		this.parentMB = parent;
		X = x;
		Y = y;
		W = w;
		H = h;
	}

	public void update() {
	}

	public void render(MatrixStack matrices, int mX, int mY) {
	}

	public void mouseDown(int mX, int mY, int mB) {
	}

	public void mouseUp(int mX, int mY) {
	}

	public void keyPress(int key) {
	}

	public void close() {
	}

	public void drawButton(MatrixStack matrices, int mX, int mY)
	{
		FrostBurn.clickGUI.drawGradient(matrices, X, Y, X + W , Y + H, new Color(ClickGuiMod.clickGuiMod.bgR.getValue(), ClickGuiMod.clickGuiMod.bgG.getValue(), ClickGuiMod.clickGuiMod.bgB.getValue(), ClickGuiMod.clickGuiMod.bgA.getValue()).getRGB(), new Color(ClickGuiMod.clickGuiMod.bgR.getValue(), ClickGuiMod.clickGuiMod.bgG.getValue(), ClickGuiMod.clickGuiMod.bgB.getValue(), ClickGuiMod.clickGuiMod.bgA.getValue()).getRGB());

		if (isHover(getX(), getY(), getW(), getH() - 1, mX, mY)) {
			FrostBurn.clickGUI.drawGradient(matrices, X, Y, X + W , Y + H, new Color(140, 140, 140, 110).getRGB(), new Color(140, 140, 140, 110).getRGB());
		}
	}

	protected boolean isHovering(final double mouseX, final double mouseY) {
		return mouseX >= this.getX() && mouseX <= this.getX() + this.getW() && mouseY >= this.getY() && mouseY <= this.getY() + this.H;
	}

	public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
		if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
			this.state = !this.state;
			this.toggle();
		}
	}

	public Setting getValue(){
		return setting;
	}

	public void toggle() {
	}

	public Module getModule()
	{
		return module;
	}

	public void setModule(Module module)
	{
		this.module = module;
	}

	public SettingButton getSelf() {
		return this;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public double getW() {
		return W;
	}

	public double getH() {
		return H;
	}

	public boolean isHover(double X, double Y, double W, double H, int mX, int mY) {
		return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
	}
}
