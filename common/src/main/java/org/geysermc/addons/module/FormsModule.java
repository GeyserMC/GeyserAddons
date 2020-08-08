package org.geysermc.addons.module;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.geysermc.addons.GeyserAddons;
import org.geysermc.addons.message.PluginMessageRecipient;
import org.geysermc.addons.util.NetworkUtils;
import org.geysermc.common.window.FormWindow;
import org.geysermc.common.window.response.FormResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The module used for sending forms to players.
 */
public class FormsModule extends AddonModule {

    private final Map<Integer, FormWindow> windows = new HashMap<>();
    private final Map<Integer, Consumer<FormResponse>> pendingResponses = new HashMap<>();

    public FormsModule(String moduleName, String description) {
        super(moduleName, description);
    }

    /**
     * Sends a form to the plugin message recipient.
     *
     * @param recipient the recipient to send the form to
     * @param form the form to send
     */
    public void sendForm(PluginMessageRecipient recipient, FormWindow form) {
        this.sendForm(recipient, form, null);
    }

    /**
     * Sends a form to the plugin message recipient.
     *
     * @param recipient the recipient to send the form to
     * @param form the form to send
     * @param response the window response
     */
    public void sendForm(PluginMessageRecipient recipient, FormWindow form, Consumer<FormResponse> response) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        try {
            int id = this.pendingResponses.size() + 1;
            NetworkUtils.writeString(buffer, this.getModuleName());
            buffer.writeInt(id);
            NetworkUtils.writeString(buffer, form.getJSONData());
            recipient.sendPluginMessage(GeyserAddons.PLUGIN_MESSAGE_CHANNEL, buffer);
            this.windows.put(id, form);
            this.pendingResponses.put(id, response);
        } finally {
            buffer.release();
        }
    }

    public void handleResponse(PluginMessageRecipient recipient, int id, String response) {
        if (!this.windows.containsKey(id)) {
            return;
        }
        FormWindow window = this.windows.remove(id);
        window.setResponse(response.trim());
        Consumer<FormResponse> windowConsumer = this.pendingResponses.remove(id);
        if (windowConsumer != null) {
            windowConsumer.accept(window.getResponse());
        }
    }

    public static FormsModule get() {
        return GeyserAddons.getInstance().getModule(FormsModule.class);
    }
}
