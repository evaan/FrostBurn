package com.evaan.frostburn.clickgui;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.Setting;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.*;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.net.URI;
import java.util.HashMap;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class ImGuiScreen extends Screen {

    private long windowPtr;

    private final ImGuiImplGlfw implGlfw = new ImGuiImplGlfw();

    private final ImGuiImplGl3 implGl3 = new ImGuiImplGl3();

    private HashMap<Module, ImBoolean> enabledMap = new HashMap<>();
    private HashMap<Setting, Object> settingsMap = new HashMap<>();

    private HashMap<Module.Category, Boolean> spaghettiCode = new HashMap<>();
    private HashMap<Module, Boolean> showSettingsMap = new HashMap<>();

    int x = 50;
    int alpha = 0;

    boolean isClosing = false;

    public ImGuiScreen() {
        super(new LiteralText("FrostBurn ClickGui"));
        windowPtr = MinecraftClient.getInstance().getWindow().getHandle();
        ImGui.createContext();
        implGlfw.init(windowPtr, false);
        implGl3.init("#version 150");

        for (Module.Category category : Module.Category.values()) {
            spaghettiCode.put(category, false);
        }

        for (Module module : ModuleManager.modules) {
            if (module.getName().equalsIgnoreCase("imgui")) continue;
            showSettingsMap.put(module, false);
            enabledMap.put(module, new ImBoolean(module.isEnabled()));
            for (Setting setting : module.settings) {
                switch (setting.getType()) {
                    case BOOLEAN:
                        settingsMap.put(setting, new ImBoolean((Boolean) setting.getValue()));
                        break;
                    case INTEGER:
                        settingsMap.put(setting, new int[]{(int) setting.getValue()});
                        break;
                    case FLOAT:
                        settingsMap.put(setting, new float[]{(float) setting.getValue()});
                        break;
                    case STRING:
                        settingsMap.put(setting, new ImInt(setting.getOptions().indexOf(setting.getValue())));
                        break;
                }
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        implGlfw.newFrame();
        ImGui.newFrame();
        if (alpha < 255) alpha+=10;
        if (isClosing) {
            alpha-=30;
            if (alpha <= 0) super.onClose();
        }
        ImGui.getIO().setConfigWindowsMoveFromTitleBarOnly(true);
        ImGui.getStyle().setWindowMenuButtonPosition(-1);
        ImGui.getStyle().setColor(ImGuiCol.TitleBgActive, 0, 0, 0, alpha);
        ImGui.getStyle().setColor(ImGuiCol.FrameBg, 50, 50, 50, alpha);
        ImGui.getStyle().setColor(ImGuiCol.FrameBgActive, 50, 50, 50, alpha);
        ImGui.getStyle().setColor(ImGuiCol.FrameBgHovered, 75, 75, 75, alpha);
        ImGui.getStyle().setColor(ImGuiCol.CheckMark, 255, 255, 255, alpha);
        ImGui.getStyle().setColor(ImGuiCol.SliderGrab, 255, 255, 255, alpha);
        ImGui.getStyle().setColor(ImGuiCol.SliderGrabActive, 255, 255, 255, alpha);
        ImGui.getStyle().setColor(ImGuiCol.Button, 50, 50,50,alpha);
        ImGui.getStyle().setColor(ImGuiCol.ButtonHovered, 75, 75,75,alpha);
        ImGui.getStyle().setColor(ImGuiCol.ButtonActive, 100, 100, 100,alpha);
        ImGui.getStyle().setColor(ImGuiCol.Header, 50, 50, 50,alpha);
        ImGui.getStyle().setColor(ImGuiCol.HeaderHovered, 75, 75, 75,alpha);
        ImGui.getStyle().setColor(ImGuiCol.Text, 255, 255, 255, alpha);
        ImGui.getStyle().setColor(ImGuiCol.WindowBg, 25, 25, 25, alpha > 220 ? 220 : alpha);
        ImGui.getStyle().setColor(ImGuiCol.Border, 0, 0, 0, alpha);
        ImGui.getStyle().setColor(ImGuiCol.TitleBg, 50, 50, 50, alpha);
        for (Module.Category category : Module.Category.values()) {
            ImGui.begin(category.getName(), ImGuiWindowFlags.NoResize);
            if (!spaghettiCode.get(category)) {
                ImGui.setWindowPos(x, 50);
                x+=300;
                ImGui.setWindowSize(250, ImGui.getWindowHeight());
                spaghettiCode.put(category, true);
            }
            for (Module module : ModuleManager.getModulesInCategory(category)) {
                if (module.getName().equalsIgnoreCase("imgui"))
                    continue;
                ImGui.checkbox(module.getName(), enabledMap.get(module));
                if (ImGui.isItemHovered() && ImGui.isMouseClicked(1)) showSettingsMap.put(module, !showSettingsMap.get(module));
                ImGui.sameLine(235);
                ImGui.text(((module.settings.isEmpty()) ? "" : (showSettingsMap.get(module)) ? "V" : ">"));
                if (showSettingsMap.get(module)) {
                    for (Setting setting : module.settings) {
                        ImGui.indent();
                        switch (setting.getType()) {
                            case BOOLEAN:
                                ImGui.checkbox(setting.getName(), (ImBoolean) settingsMap.get(setting));
                                if ((boolean) setting.getValue() != ((ImBoolean) settingsMap.get(setting)).get())
                                    setting.setValue(((ImBoolean) settingsMap.get(setting)).get());
                                break;
                            case INTEGER:
                                ImGui.sliderInt(setting.getName(), (int[]) settingsMap.get(setting), (int) setting.getMin(), (int) setting.getMax());
                                int[] javaStupid = (int[]) settingsMap.get(setting);
                                if (javaStupid[0] != (int) setting.getValue()) setting.setValue(javaStupid[0]);
                                break;
                            case FLOAT:
                                ImGui.sliderFloat(setting.getName(), (float[]) settingsMap.get(setting), (float) setting.getMin(),(float) setting.getMax());
                                float[] javaStupid1 = (float[]) settingsMap.get(setting);
                                if (javaStupid1[0] != (float) setting.getValue()) setting.setValue(javaStupid1[0]);
                                break;
                            case STRING:
                                String[] javaStupid2 = (String[]) setting.getOptions().toArray(new String[setting.getOptions().size()]);
                                ImGui.combo(setting.getName(), (ImInt) settingsMap.get(setting), javaStupid2);
                                if (((ImInt) settingsMap.get(setting)).get() != setting.getOptions().indexOf(setting.getValue()))
                                    setting.setValue(setting.getOptions().get(((ImInt) settingsMap.get(setting)).get()));
                        }
                        ImGui.unindent();
                    }

                }
                if (enabledMap.get(module).get() != module.isEnabled()) module.toggle();
            }
            ImGui.end();
        }

        ImGui.render();
        implGl3.renderDrawData(Objects.requireNonNull(ImGui.getDrawData()));
    }

    @Override
    public void onClose() {
        if (isClosing) super.onClose();
        else isClosing = true;
    }
}