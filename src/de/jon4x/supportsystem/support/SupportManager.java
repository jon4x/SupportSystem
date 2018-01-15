package de.jon4x.supportsystem.support;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupportManager {

    private static SupportManager instance;
    private List<ProxiedPlayer> supportQueue;
    private Map<ProxiedPlayer, ProxiedPlayer> supportPlayer;

    public SupportManager() {
        supportQueue = new ArrayList<>();
        supportPlayer = new HashMap<>();
    }

    public static SupportManager getInstance() {
        if (instance == null) {
            instance = new SupportManager();
        }
        return instance;
    }

    public List<ProxiedPlayer> getSupportQueue() {
        return this.supportQueue;
    }

    public Map<ProxiedPlayer, ProxiedPlayer> getSupportPlayer() {
        return this.supportPlayer;
    }

    public void sendToTeam(ProxiedPlayer needSupport) {
        for (final ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
            if (p.hasPermission("system.support")) {
                TextComponent tc = new TextComponent();
                tc.setColor(ChatColor.GREEN);
                tc.setText("[ANNEHMEN]");
                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7§oKlicken um zu supporten.").create()));
                tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/support accept " + needSupport.getName()));
                TextComponent realTc = new TextComponent("§8» §aSupport §8× §7Der §7Spieler §e" + needSupport.getName() + " §7benötigt §7Hilfe.\n");
                realTc.addExtra(tc);
                p.sendMessage(realTc);
            }
        }
    }

}
