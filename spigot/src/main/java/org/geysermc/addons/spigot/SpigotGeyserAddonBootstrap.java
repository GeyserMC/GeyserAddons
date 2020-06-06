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

package org.geysermc.addons.spigot;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.geysermc.addons.GeyserAddonBootstrap;
import org.geysermc.addons.GeyserAddons;
import org.geysermc.addons.spigot.command.SpigotCommandSource;
import org.geysermc.addons.command.AddonCommand;

import java.lang.reflect.Field;

public class SpigotGeyserAddonBootstrap extends JavaPlugin implements GeyserAddonBootstrap {

    private static CommandMap COMMAND_MAP;

    static {
        try {
            // Why Bukkit
            Field cmdMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            cmdMapField.setAccessible(true);
            COMMAND_MAP = (CommandMap) cmdMapField.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onLoad() {
        GeyserAddons.setInstance(new GeyserAddons(this));
    }

    @Override
    public void onEnable() {
        GeyserAddons.getInstance().onEnable();
    }

    @Override
    public void onDisable() {
       GeyserAddons.getInstance().onDisable();
    }

    @Override
    public void registerCommand(AddonCommand addonCommand) {
        Command command = new Command(addonCommand.getCommand()) {

            @Override
            public boolean execute(CommandSender commandSender, String label, String[] args) {
                addonCommand.getExecutor().execute(new SpigotCommandSource(commandSender), args);
                return true;
            }
        };
        command.setName(addonCommand.getCommand());
        command.setDescription(addonCommand.getDescription());
        command.setPermission(addonCommand.getPermission());
        command.setAliases(addonCommand.getAliases());
        COMMAND_MAP.register(addonCommand.getCommand(), "geyseraddons", command);
    }
}
