package fr.blixow.celestiamsanction.commands;

import fr.blixow.celestiamsanction.PermissionList;
import fr.blixow.celestiamsanction.api.CheckUtils;
import fr.blixow.celestiamsanction.api.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MuteCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission(PermissionList.MUTE_PERM)) {
            if (args.length >= 3) {
                if (CheckUtils.isPlayerExist(args[0])) {
                    UUID target = CheckUtils.isPlayerOnline(args[0]) ? Bukkit.getPlayer(args[0]).getUniqueId() : Bukkit.getOfflinePlayer(args[0]).getUniqueId();
                    if(!StringUtils.isValid(args[1])){
                        sender.sendMessage("§cdate pas valide");
                        return true;
                    }
                    long time = StringUtils.stringToUnixInteger(args[1]) + System.currentTimeMillis();
                    StringBuilder reason = new StringBuilder();
                    for(int i = 2; i < args.length; i++) { reason.append(args[i]).append(" "); }
                    String raison = reason.toString();
                    raison = raison.substring(0, raison.length() - 1);

                    Date d = new Date(time);
                    sender.sendMessage("§fMute informations :");
                    sender.sendMessage("§e» §fCible = §b" + args[0]);
                    sender.sendMessage("§e» §fRaison = §b" + raison);
                    sender.sendMessage("§e» §fAnnée = §b" + d.getYear());
                    sender.sendMessage("§e» §fMois = §b" + d.getMonth());
                    sender.sendMessage("§e» §fJour = §b" + d.getDate());
                    sender.sendMessage("§e» §fMinutes = §b" + d.getMinutes());
                    sender.sendMessage("§e» §fSeconde = §b" + d.getSeconds());
                    return true;
                }
            } else {
                sender.sendMessage("§c/mute <Joueur> <Temps> [Raison]");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
