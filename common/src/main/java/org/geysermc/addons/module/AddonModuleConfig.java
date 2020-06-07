package org.geysermc.addons.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class AddonModuleConfig {

    public static AddonModuleConfig load(Logger logger, Path configPath) {
        return load(logger, configPath, AddonModuleConfig.class);
    }

    public static <T extends AddonModuleConfig> T load(Logger logger, Path configPath, Class<T> configClass) {
        T config = null;
        try {
            try {
                if (!configPath.toFile().exists()) {
                    // Create the folder to store the config path
                    configPath.toFile().getParentFile().mkdirs();

                    Files.copy(AddonModuleConfig.class.getClassLoader().getResourceAsStream("modules/" + configPath.getFileName()), configPath);
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error while creating config", e);
            }

            config = new ObjectMapper(new YAMLFactory()).readValue(
                    Files.readAllBytes(configPath), configClass
            );
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while loading config", e);
        }

        if (config == null) {
            throw new RuntimeException("Failed to load config file! Try to delete the data folder of Floodgate");
        }

        return config;
    }
}
