package org.geysermc.addons.spigot.modules.placeholders;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.geysermc.addons.module.AddonModuleConfig;

@Getter
public class PlaceholderModuleConfig extends AddonModuleConfig {
    @JsonProperty(value = "specific-device-descriptors")
    private boolean specificDeviceDescriptors;

    @JsonProperty(value = "device")
    private DevicePlaceholders device;

    @JsonProperty(value = "locale")
    private GenericPlaceholders locale;

    @JsonProperty(value = "version")
    private GenericPlaceholders version;

    @JsonProperty(value = "xbox-username")
    private GenericPlaceholders xboxUsername;

    @JsonProperty(value = "xbox-xuid")
    private GenericPlaceholders xboxXuid;

    @Getter
    public static class DevicePlaceholders {
        @JsonProperty("java")
        private String java;

        @JsonProperty("generic")
        private String generic;

        @JsonProperty("unknown")
        private String unknown;

        @JsonProperty("android")
        private String android;

        @JsonProperty("ios")
        private String iOS;

        @JsonProperty("osx")
        private String OSX;

        @JsonProperty("fireos")
        private String fireos;

        @JsonProperty("gearvr")
        private String gearVR;

        @JsonProperty("hololens")
        private String hololens;

        @JsonProperty("win10")
        private String win10;

        @JsonProperty("win32")
        private String win32;

        @JsonProperty("dedicated")
        private String dedicated;

        @JsonProperty("orbis")
        private String orbis;

        @JsonProperty("nx")
        private String NX;

        @JsonProperty("switch")
        private String nintendoSwitch;

        @JsonProperty("xboxOne")
        private String xboxOne;
    }

    @Getter
    public static class GenericPlaceholders {
        @JsonProperty("found")
        private String found;

        @JsonProperty("none")
        private String none;
    }
}
