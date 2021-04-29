package com.evaan.frostburn.imgui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.Setting;
import imgui.ImGui;
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

@Environment(EnvType.CLIENT)
public class NewGui extends Screen {

    private long windowPtr;

    private final ImGuiImplGlfw implGlfw = new ImGuiImplGlfw();

    private final ImGuiImplGl3 implGl3 = new ImGuiImplGl3();

    private HashMap<Module, ImBoolean> enabledMap = new HashMap<>();
    private HashMap<Setting, Object> settingsMap = new HashMap<>();

    public NewGui() {
        super(new LiteralText("FrostBurn ClickGui"));
        windowPtr = MinecraftClient.getInstance().getWindow().getHandle();
        ImGui.createContext();
        implGlfw.init(windowPtr, false);
        implGl3.init("#version 150");

        for (Module module : ModuleManager.modules) {
            if (module.getName().equalsIgnoreCase("imgui") || module.getName().equalsIgnoreCase("clickgui")) continue;
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
        textRenderer.drawWithShadow(matrices, new LiteralText(Formatting.BLUE+"Frost"+Formatting.RED+"Burn"+Formatting.WHITE+" 1.0 by evaan"), 2, 2, 0xffffff);
        textRenderer.drawWithShadow(matrices, new LiteralText("https://github.com/evaan/FrostBurn"), 2, 12, 0xffffff);
        implGlfw.newFrame();
        ImGui.newFrame();
        for (Module.Category category : Module.Category.values()) {
            ImGui.begin(category.getName());
            for (Module module : ModuleManager.getModulesInCategory(category)) {
                if (module.getName().equalsIgnoreCase("imgui") || module.getName().equalsIgnoreCase("clickgui"))
                    continue;
                ImGui.checkbox(module.getName(), enabledMap.get(module));
                if (enabledMap.get(module).get() != module.isEnabled()) module.toggle();
                if (!module.settings.isEmpty() && ImGui.collapsingHeader(module.getName() + " Settings")) {
                    for (Setting setting : module.settings) {
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
                    }
                }
            }
            ImGui.end();
        }
        ImGui.render();
        implGl3.renderDrawData(Objects.requireNonNull(ImGui.getDrawData()));
    }
}