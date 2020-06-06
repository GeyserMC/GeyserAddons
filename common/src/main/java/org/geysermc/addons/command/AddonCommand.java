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
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.addons.command;

import lombok.Builder;

import java.util.List;

/**
 * Command class for Geyser addons.
 */
@Builder
public class AddonCommand {

    private String command;
    private String description;
    private String permission;
    private List<String> aliases;
    private CommandExecutor executor;

    /**
     * Gets the command.
     *
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Gets the command description.
     *
     * @return the command description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the permission for this command.
     *
     * @return the command permission
     */
    public String getPermission() {
        return this.permission;
    }

    /**
     * Gets the aliases of the command.
     *
     * @return the aliases of the command
     */
    public List<String> getAliases() {
        return this.aliases;
    }

    /**
     * Gets the executor for this command.
     *
     * @return the executor for this command
     */
    public CommandExecutor getExecutor() {
        return this.executor;
    }
}
