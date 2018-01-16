package de.jon4x.supportsystem.listener;

import de.jon4x.supportsystem.SupportSystem;
import de.jon4x.supportsystem.support.SupportManager;
import de.jon4x.supportsystem.utils.Methods;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        if (!e.getMessage().startsWith("/")) {
            if (SupportManager.getInstance().getSupportPlayer().containsKey(p)) {
                e.setCancelled(true);
                p.sendMessage(new TextComponent(SupportSystem.getPrefix() + "§a" + p.getName() + " §8\u00bb §7" + e.getMessage()));

                ProxiedPlayer supportedPlayer = SupportManager.getInstance().getSupportPlayer().get(p);
                supportedPlayer.sendMessage(new TextComponent(SupportSystem.getPrefix() + "§a" + p.getName() + " §8\u00bb §7" + e.getMessage()));
            }
            else if (SupportManager.getInstance().getSupportPlayer().containsValue(p)) {
                e.setCancelled(true);
                p.sendMessage(new TextComponent(SupportSystem.getPrefix() + "§7" + p.getName() + " §8\u00bb §7" + e.getMessage()));

                ProxiedPlayer supporter = (ProxiedPlayer) Methods.getKeyFromValue(SupportManager.getInstance().getSupportPlayer(), p);
                assert supporter != null;
                supporter.sendMessage(new TextComponent(SupportSystem.getPrefix() + "§7" + p.getName() + " §8\u00bb §7" + e.getMessage()));
            }
        }
    }
}
