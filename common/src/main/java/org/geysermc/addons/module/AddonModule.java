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

package org.geysermc.addons.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.geysermc.addons.GeyserAddonBootstrap;

/**
 * Represents an addon module. Each "addon" for Geyser Addons is
 * stored in a separate module each with their own loading, config,
 * and command classes.
 */
@Getter
@AllArgsConstructor
public class AddonModule {

    private GeyserAddonBootstrap bootstrap;
    private String moduleName;
    private String description;

    public void onEnable() { }

    public void onDisable() { }

    public <T extends AddonModuleConfig> T loadConfig(Class<T> configClass) {
        return AddonModuleConfig.load(bootstrap.getAddonLogger(), bootstrap.getConfigFolder().resolve("modules").resolve(moduleName + ".yml"), configClass);
    }
}
