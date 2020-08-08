package org.geysermc.addons.bungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.geysermc.addons.bungee.message.BungeePluginMessageRecipient;
import org.geysermc.addons.message.PluginMessageRecipient;

/**
 * Adapter to quickly convert from Geyser to BungeeCord or
 * BungeeCord to Geyser classes.
 */
public class BungeeAdapters {

    public static PluginMessageRecipient of(ProxiedPlayer handle) {
        return BungeePluginMessageRecipient.of(handle);
    }
}
