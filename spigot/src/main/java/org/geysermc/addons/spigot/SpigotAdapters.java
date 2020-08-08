package org.geysermc.addons.spigot;

import org.geysermc.addons.message.PluginMessageRecipient;
import org.geysermc.addons.spigot.message.SpigotPluginMessageRecipient;

/**
 * Adapter to quickly convert from Geyser to Spigot or
 * Spigot to Geyser classes.
 */
public class SpigotAdapters {

    public static PluginMessageRecipient of(org.bukkit.plugin.messaging.PluginMessageRecipient handle) {
        return SpigotPluginMessageRecipient.of(handle);
    }
}
