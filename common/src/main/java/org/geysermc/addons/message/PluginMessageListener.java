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

package org.geysermc.addons.message;

import io.netty.buffer.ByteBuf;
import org.geysermc.addons.GeyserAddons;
import org.geysermc.addons.module.FormsModule;
import org.geysermc.addons.util.NetworkUtils;

/**
 * A listener for when receiving plugin messages.
 */
public class PluginMessageListener {

    /**
     * Called when a plugin message is received.
     *
     * @param recipient the recipient of the plugin message
     * @param channel the channel the plugin message was received on
     * @param buffer the plugin message received
     */
    public void onMessageReceive(PluginMessageRecipient recipient, String channel, ByteBuf buffer) {
        if (!channel.equals(GeyserAddons.PLUGIN_MESSAGE_CHANNEL)) {
            return;
        }
        String subChannel = NetworkUtils.readString(buffer);
        if (subChannel.equals(FormsModule.get().getModuleName())) {
            FormsModule.get().handleResponse(recipient, buffer.readInt(), NetworkUtils.readString(buffer));
        }
    }
}
