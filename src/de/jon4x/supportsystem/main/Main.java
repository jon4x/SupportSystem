package de.jon4x.supportsystem.main;

import de.jon4x.supportsystem.commands.SupportCommand;
import de.jon4x.supportsystem.listener.ChatEvent;
import de.jon4x.supportsystem.listener.DisconnectEvent;
import de.jon4x.supportsystem.support.SupportManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Main extends Plugin {

    // On Enable
    @Override
    public void onEnable() {
        getProxy().getConsole().sendMessage(getPrefix() + "§aDas Support-System wurde aktiviert!");
        PluginManager pm = getProxy().getPluginManager();
        registerCommands(pm);
        registerEvents(pm);
    }

    // On Disable
    @Override
    public void onDisable() {
        getProxy().getConsole().sendMessage(getPrefix() + "§cDas Support-System wurde deaktiviert!");
    }

    // Register Events
    private void registerEvents(PluginManager pm) {
        pm.registerListener(this, new ChatEvent());
        pm.registerListener(this, new DisconnectEvent());
    }

    // Register Commands
    private void registerCommands(PluginManager pm) {
        pm.registerCommand(this, new SupportCommand());
    }

    // Getter
    public static String getPrefix() {
        return "§8[§aSupport§8] ";
    }
}
