package org.geysermc.addons.spigot.modules.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.geysermc.addons.spigot.SpigotGeyserAddonBootstrap;
import org.geysermc.floodgate.FloodgateAPI;
import org.geysermc.floodgate.FloodgatePlayer;

public class Placeholders extends PlaceholderExpansion {

    private SpigotGeyserAddonBootstrap plugin;
    private PlaceholderModule module;

    public Placeholders(PlaceholderModule module, SpigotGeyserAddonBootstrap plugin){
        this.module = module;
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "Floodgate";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if(player == null) {
            return "";
        }

        switch (identifier) {
            case "device":
                return getPlayerDeviceString(player);

            case "locale":
            case "locale_upper":
                if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
                    FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                    boolean upper = identifier.endsWith("_upper");
                    return module.getConfiguration().getLocale().getFound().replace("%locale%", upper ? floodgatePlayer.getLanguageCode().toUpperCase() : floodgatePlayer.getLanguageCode().toLowerCase());
                } else {
                    return module.getConfiguration().getLocale().getNone();
                }

            case "version":
                if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
                    FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                    return module.getConfiguration().getVersion().getFound().replace("%version%", floodgatePlayer.getVersion());
                } else {
                    return module.getConfiguration().getVersion().getNone();
                }

            case "username":
                if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
                    FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                    return module.getConfiguration().getXboxUsername().getFound().replace("%username%", floodgatePlayer.getUsername());
                } else {
                    return module.getConfiguration().getXboxUsername().getNone();
                }

            case "xuid":
                if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
                    FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                    return module.getConfiguration().getXboxXuid().getFound().replace("%xuid%", floodgatePlayer.getXuid());
                } else {
                    return module.getConfiguration().getXboxXuid().getNone();
                }
        }

        return null;
    }

    /**
     * Get the device string from config for the specified player
     *
     * @param player The player to get the device for
     * @return The formatted device string from config
     */
    private String getPlayerDeviceString(Player player) {
        if (FloodgateAPI.isBedrockPlayer(player.getUniqueId())) {
            if (module.getConfiguration().isSpecificDeviceDescriptors()) {
                FloodgatePlayer floodgatePlayer = FloodgateAPI.getPlayer(player.getUniqueId());
                switch (floodgatePlayer.getDeviceOS()) {
                    case ANDROID:
                        return module.getConfiguration().getDevice().getAndroid().replace("&", "§");
                    case IOS:
                        return module.getConfiguration().getDevice().getIOS().replace("&", "§");
                    case OSX:
                        return module.getConfiguration().getDevice().getOSX().replace("&", "§");
                    case FIREOS:
                        return module.getConfiguration().getDevice().getFireos().replace("&", "§");
                    case GEARVR:
                        return module.getConfiguration().getDevice().getGearVR().replace("&", "§");
                    case HOLOLENS:
                        return module.getConfiguration().getDevice().getHololens().replace("&", "§");
                    case WIN10:
                        return module.getConfiguration().getDevice().getWin10().replace("&", "§");
                    case WIN32:
                        return module.getConfiguration().getDevice().getWin32().replace("&", "§");
                    case DEDICATED:
                        return module.getConfiguration().getDevice().getDedicated().replace("&", "§");
                    case ORBIS:
                        return module.getConfiguration().getDevice().getOrbis().replace("&", "§");
                    case NX:
                        return module.getConfiguration().getDevice().getNX().replace("&", "§");
                    case SWITCH:
                        return module.getConfiguration().getDevice().getNintendoSwitch().replace("&", "§");
                    case XBOX_ONE:
                        return module.getConfiguration().getDevice().getXboxOne().replace("&", "§");
                    default:
                        return module.getConfiguration().getDevice().getUnknown().replace("&", "§");
                }
            }else{
                return module.getConfiguration().getDevice().getGeneric().replace("&", "§");
            }
        } else {
            return module.getConfiguration().getDevice().getJava().replace("&", "§");
        }
    }

}
