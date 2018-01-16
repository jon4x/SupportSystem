package de.jon4x.supportsystem;

import de.jon4x.supportsystem.commands.SupportCommand;
import de.jon4x.supportsystem.listener.ChatListener;
import de.jon4x.supportsystem.listener.DisconnectListener;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class SupportSystem extends Plugin {

    // On Enable
    @Override
    public void onEnable() {
        getProxy().getConsole().sendMessage(new TextComponent(getPrefix() + "§7Das Support-System wurde §aaktiviert§7!"));
        PluginManager pm = getProxy().getPluginManager();
        registerCommands(pm);
        registerEvents(pm);
    }

    // On Disable
    @Override
    public void onDisable() {
        getProxy().getConsole().sendMessage(new TextComponent(getPrefix() + "§7Das Support-System wurde §cdeaktiviert§7!"));
    }

    // Register Events
    private void registerEvents(PluginManager pm) {
        pm.registerListener(this, new ChatListener());
        pm.registerListener(this, new DisconnectListener());
    }

    // Register Commands
    private void registerCommands(PluginManager pm) {
        pm.registerCommand(this, new SupportCommand());
    }

    // Getter
    public static String getPrefix() {
        return "§2•§a● Support §8\u00bb ";
    }
}
