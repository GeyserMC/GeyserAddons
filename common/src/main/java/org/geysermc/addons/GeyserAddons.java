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

import lombok.RequiredArgsConstructor;
import org.geysermc.addons.command.AddonCommand;
import org.geysermc.addons.module.AddonModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Core class for the Geyser addons plugin. For the main
 * classes of other platforms, look in their modules.
 */
public class GeyserAddons {

    private static GeyserAddons INSTANCE;

    private final GeyserAddonBootstrap bootstrap;

    private List<AddonModule> modules = new ArrayList<>();

    public GeyserAddons(GeyserAddonBootstrap bootstrap) {
        this.bootstrap = bootstrap;

        AddonCommand.AddonCommandBuilder reloadCommand = AddonCommand.builder();
        reloadCommand.command("reload");
        reloadCommand.description("Reloads all modules");
        reloadCommand.permission("geyseraddons.command.reload");
        reloadCommand.aliases(new ArrayList<>());
        reloadCommand.executor((source, args) -> {
            this.onDisable();
            this.onEnable();
        });

        registerCommand(reloadCommand.build());
    }

    /**
     * Code that is ran when this plugin is enabled.
     */
    public void onEnable() {
        for (AddonModule module : modules) {
            if (module.getConfiguration().isEnabled()) {
                module.onEnable();
            }
        }
    }

    /**
     * Code that is ran when this plugin is disabled.
     */
    public void onDisable() {
        for (AddonModule module : modules) {
            if (module.getConfiguration().isEnabled()) {
                module.onDisable();
            }
        }
    }

    /**
     * Registers a {@link AddonModule}.
     *
     * @param module the module to register
     */
    public void registerModule(AddonModule module) {
        this.modules.add(module);
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
