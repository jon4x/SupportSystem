package de.jon4x.supportsystem.listener;

import de.jon4x.supportsystem.SupportSystem;
import de.jon4x.supportsystem.support.SupportManager;
import de.jon4x.supportsystem.utils.Methods;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisconnectListener implements Listener {

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent e) {
        if (SupportManager.getInstance().getSupportQueue().contains(e.getPlayer())) {
            for (ProxiedPlayer team : ProxyServer.getInstance().getPlayers()) {
                if (team.hasPermission("system.support")) {
                    team.sendMessage(new TextComponent(SupportSystem.getPrefix() + "§7Der Spieler §c" + e.getPlayer().getName() + " §7hat die Warteschlange verlassen!"));
                    SupportManager.getInstance().getSupportQueue().remove(e.getPlayer());
                }
            }
        }
        if (SupportManager.getInstance().getSupportPlayer().containsKey(e.getPlayer())) {
            ProxiedPlayer supportedPlayer = SupportManager.getInstance().getSupportPlayer().get(e.getPlayer());
            supportedPlayer.sendMessage(new TextComponent(SupportSystem.getPrefix() + "§7Der §7Supporter §e" + e.getPlayer() + " §7hat §7den §7Server §7verlassen. §7Der §7Support §7wurde §7somit §cbeendet§7."));

            SupportManager.getInstance().getSupportPlayer().remove(e.getPlayer());
            SupportManager.getInstance().getSupportPlayer().remove(supportedPlayer);
        }
        if (SupportManager.getInstance().getSupportPlayer().containsValue(e.getPlayer())) {
            ProxiedPlayer supporter = (ProxiedPlayer) Methods.getKeyFromValue(SupportManager.getInstance().getSupportPlayer(), e.getPlayer());
            assert supporter != null;
            supporter.sendMessage(new TextComponent(SupportSystem.getPrefix() + "§7Der Spieler §c" + e.getPlayer() + " §7hat §7den §7Server §7verlassen. §7Der §7Support §7wurde §7somit §cbeendet§7."));

            SupportManager.getInstance().getSupportPlayer().remove(e.getPlayer());
            SupportManager.getInstance().getSupportPlayer().remove(supporter);
        }
    }
}
