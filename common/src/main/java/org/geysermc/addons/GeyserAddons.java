/*
 * Copyright (c) 2019-2020 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/GeyserAddons
 */

package org.geysermc.addons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.geysermc.addons.command.AddonCommand;
import org.geysermc.addons.message.PluginMessageListener;
import org.geysermc.addons.module.AddonModule;
import org.geysermc.addons.module.FormsModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Core class for the Geyser addons plugin. For the main
 * classes of other platforms, look in their modules.
 */
@Getter
@RequiredArgsConstructor
public class GeyserAddons {

    private static GeyserAddons INSTANCE;

    public static final String PLUGIN_MESSAGE_CHANNEL = "geyser:addons";

    @Getter(AccessLevel.NONE)
    private final GeyserAddonBootstrap bootstrap;

    private PluginMessageListener pluginMessageListener = new PluginMessageListener();
    private Map<Class<? extends AddonModule>, AddonModule> modules = new HashMap<>();

    /**
     * Code that is ran when this plugin is enabled.
     */
    public void onEnable() {
        this.registerModule(new FormsModule("form", "Module for managing forms with bedrock players. "));
    }

    /**
     * Code that is ran when this plugin is disabled.
     */
    public void onDisable() {

    }

    /**
     * Registers a module.
     *
     * @param module the module to register
     */
    public <T extends AddonModule> void registerModule(T module) {
        this.registerModule(module.getClass(), module);
    }

    /**
     * Registers a module.
     *
     * @param moduleClass the class of the module
     * @param module the module to register
     */
    public <T extends AddonModule> void registerModule(Class<? extends T> moduleClass, T module) {
        this.modules.put(moduleClass, module);
    }

    public <T extends AddonModule> T getModule(Class<? extends T> moduleClass) {
        return moduleClass.cast(this.modules.get(moduleClass));
    }

    /**
     * Registers an {@link AddonCommand} to the server.
     *
     * @param command the command to register
     */
    public void registerCommand(AddonCommand command) {
        this.bootstrap.registerCommand(command);
    }

    /**
     * Returns the current {@link GeyserAddons} instance.
     *
     * @return the current instance set
     */
    public static GeyserAddons getInstance() {
        return INSTANCE;
    }

    /**
     * Sets the current {@link GeyserAddons} instance.
     *
     * @param instance the instance to set
     */
    public static void setInstance(GeyserAddons instance) {
        if (INSTANCE != null) {
            throw new RuntimeException("Cannot redefine singleton GeyserAddons!");
        }
        INSTANCE = instance;
    }
}
