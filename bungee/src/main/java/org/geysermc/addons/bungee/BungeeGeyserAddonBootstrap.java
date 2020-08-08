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

package org.geysermc.addons.bungee;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import org.geysermc.addons.GeyserAddonBootstrap;
import org.geysermc.addons.GeyserAddons;
import org.geysermc.addons.bungee.command.BungeeCommandSource;
import org.geysermc.addons.bungee.message.BungeePluginMessageListener;
import org.geysermc.addons.command.AddonCommand;

public class BungeeGeyserAddonBootstrap extends Plugin implements GeyserAddonBootstrap {

    @Override
    public void onLoad() {
        GeyserAddons.setInstance(new GeyserAddons(this));
    }

    @Override
    public void onEnable() {
        GeyserAddons.getInstance().onEnable();

        this.getProxy().registerChannel(GeyserAddons.PLUGIN_MESSAGE_CHANNEL);
        this.getProxy().getPluginManager().registerListener(this, new BungeePluginMessageListener(GeyserAddons.getInstance()));
    }

    @Override
    public void onDisable() {
        GeyserAddons.getInstance().onDisable();
    }

    @Override
    public void registerCommand(AddonCommand addonCommand) {
        Command command = new Command(addonCommand.getCommand(), addonCommand.getPermission(), addonCommand.getAliases().toArray(new String[0])) {

            @Override
            public void execute(CommandSender sender, String[] args) {
                addonCommand.getExecutor().execute(new BungeeCommandSource(sender), args);
            }
        };
        this.getProxy().getPluginManager().registerCommand(this, command);
    }
}
