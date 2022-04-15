package tech.williamist.shadowartifacts.util;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;

public final class MessageUtils {

    /**
     * Displays a message to the chosen player's client. You can choose whether to display it in the actionbar or in chat.
     *
     * I'm fairly sure that this will only work on the local player's client, since it is a client only function.
     * So unless you do packet stuff, you probably can't display stuff on other player's clients.
     *
     * @param player The player to display the message to.
     * @param message The message to display.
     * @param isActionbar Whether to display the message in the actionbar or in chat.
     *
     */
    public static void sendClientMessage(Player player, String message, boolean isActionbar) {
        player.displayClientMessage(new TextComponent(message), isActionbar);
    }

}
