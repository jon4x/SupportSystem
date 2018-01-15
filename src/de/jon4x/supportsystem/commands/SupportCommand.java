package de.jon4x.supportsystem.commands;

import de.jon4x.supportsystem.main.Main;
import de.jon4x.supportsystem.support.SupportManager;
import de.jon4x.supportsystem.utils.Methods;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SupportCommand extends Command{

    public SupportCommand() {
        super("support", null);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (p.hasPermission("system.support")) {

                if (args.length == 0) {
                    p.sendMessage(new TextComponent(Main.getPrefix() + "§cDu §ckannst §cals §eTeammitglied §cden Support nicht verwenden!"));
                    p.sendMessage(new TextComponent("\n§7Nutze §c/support accept <player>"));
                    p.sendMessage(new TextComponent("§7Nutze §c/support finish"));
                    p.sendMessage(new TextComponent("§7Nutze §c/"));
                    return;
                }

                if (args[0].equalsIgnoreCase("accept")) {

                    if (args.length == 1) {
                        p.sendMessage(new TextComponent(Main.getPrefix() + "§7Bitte nutze §c/support accept <player>"));
                    }

                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

                    if (!SupportManager.getInstance().getSupportPlayer().containsKey(p)) {
                        if (target != null) {
                            if (!SupportManager.getInstance().getSupportPlayer().containsValue(target)) {
                                if (SupportManager.getInstance().getSupportQueue().contains(target)) {
                                    target.sendMessage(new TextComponent(Main.getPrefix() + "§7Du §7wirst §7nun §7in §7einem §7privaten §7Chat §7betreut.\n§aDein §aSupporter §8\u00bb §e" + p.getName()));
                                    p.sendMessage(new TextComponent(Main.getPrefix() + "§7Du §7betreust §7nun §7einen §7Spieler §7in §7einem §7privaten §7Chat.\n§aDein §aSpieler §8\u00bb §e" + target.getName()));
                                }
                                else {
                                    p.sendMessage(new TextComponent());
                                }
                            }
                            else {
                                p.sendMessage(new TextComponent());
                            }
                        }
                        else {
                            p.sendMessage(new TextComponent());
                        }
                    }
                    else {
                        p.sendMessage(new TextComponent());
                    }
                }

                if (args[0].equalsIgnoreCase("finish")) {
                    if (SupportManager.getInstance().getSupportPlayer().containsKey(p)) {
                        ProxiedPlayer target = SupportManager.getInstance().getSupportPlayer().get(p);
                        SupportManager.getInstance().getSupportPlayer().remove(target);
                        SupportManager.getInstance().getSupportPlayer().remove(p);
                        p.sendMessage(new TextComponent(Main.getPrefix() + "§7Der §aSupport-Chat §7wurde §7erfolgreich §7geschlossen."));
                        target.sendMessage(new TextComponent(Main.getPrefix() + "§7Der §aSupport-Chat §7wurde §7geschlossen."));
                    }
                }

            }

            else {
                if (args.length == 0) {
                    SupportManager.getInstance().getSupportQueue().add(p);
                    SupportManager.getInstance().sendToTeam(p);
                    p.sendMessage(new TextComponent(Main.getPrefix() + "§7Du §7wurdest §7zur §aWarteschlange §7hinzugefügt.\n§7Bitte §7habe §7einen §7moment §7Geduld."));
                }
                if (args[0].equalsIgnoreCase("leave")) {
                    if (SupportManager.getInstance().getSupportQueue().contains(p)) {
                        p.sendMessage(new TextComponent(Main.getPrefix() + "§cSie haben die Warteschlange verlassen!"));
                        SupportManager.getInstance().getSupportQueue().remove(p);
                    }
                    else if (SupportManager.getInstance().getSupportPlayer().containsValue(p)) {
                        ProxiedPlayer target = (ProxiedPlayer) Methods.getKeyFromValue(SupportManager.getInstance().getSupportPlayer(), p);
                        SupportManager.getInstance().getSupportPlayer().remove(p);
                        SupportManager.getInstance().getSupportPlayer().remove(target);
                    }
                }
            }
        }
    }
}
