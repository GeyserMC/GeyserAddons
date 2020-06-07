package org.geysermc.addons.spigot.modules.placeholders;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.geysermc.addons.module.AddonModule;
import org.geysermc.addons.spigot.SpigotGeyserAddonBootstrap;

public class PlaceholderModule extends AddonModule {

    @Getter
    private PlaceholderModuleConfig configuration;

    public PlaceholderModule(SpigotGeyserAddonBootstrap bootstrap) {
        super(bootstrap, "placeholders", "Adds PlaceholderAPI support for Floodgate");

        configuration = loadConfig(PlaceholderModuleConfig.class);
    }

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("floodgate-bukkit") != null && Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholders(this, (SpigotGeyserAddonBootstrap) this.getBootstrap()).register();
        }
    }
}
