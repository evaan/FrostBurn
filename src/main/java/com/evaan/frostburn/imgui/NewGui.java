package com.evaan.frostburn.imgui;

import java.util.HashMap;
import java.util.Objects;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImBoolean;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

@Environment(EnvType.CLIENT)
public class NewGui extends Screen {

    private long windowPtr;

    private final ImGuiImplGlfw implGlfw = new ImGuiImplGlfw();

    private final ImGuiImplGl3 implGl3 = new ImGuiImplGl3();

    private HashMap<Module, ImBoolean> enabledMap = new HashMap<>();

    public NewGui() {
        super(new LiteralText("FrostBurn ClickGui"));
        windowPtr = MinecraftClient.getInstance().getWindow().getHandle();
        ImGui.createContext();
        implGlfw.init(windowPtr, false);
        implGl3.init("#version 150");
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        implGlfw.newFrame();
        ImGui.newFrame();
        for (Module module : ModuleManager.modules) {
            if (module.getName().equalsIgnoreCase("imgui") || module.getName().equalsIgnoreCase("clickgui")) continue;
            enabledMap.put(module, new ImBoolean(module.isEnabled()));
        }
        for (Module.Category category : Module.Category.values()) {
            ImGui.begin(category.getName());
            for (Module module : ModuleManager.getModulesInCategory(category)) {
                if (module.getName().equalsIgnoreCase("imgui") || module.getName().equalsIgnoreCase("clickgui")) continue;
                ImGui.checkbox(module.getName(), enabledMap.get(module));
                if (enabledMap.get(module).get() != module.isEnabled()) module.toggle();
            }
            ImGui.end();
        }
        ImGui.render();
        implGl3.renderDrawData(Objects.requireNonNull(ImGui.getDrawData()));
    }
}