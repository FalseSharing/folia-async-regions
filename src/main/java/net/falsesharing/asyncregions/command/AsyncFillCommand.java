package net.falsesharing.asyncregions.command;

import net.falsesharing.asyncregions.FoliaAsyncRegions;
import net.falsesharing.asyncregions.task.RegionBlockTask;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class AsyncFillCommand implements CommandExecutor {
    private final FoliaAsyncRegions plugin;
    private final RegionBlockTask blockTask;

    public AsyncFillCommand(FoliaAsyncRegions plugin) {
        this.plugin = plugin;
        this.blockTask = new RegionBlockTask(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can run regional fill tasks.");
            return true;
        }

        if (!player.hasPermission("asyncregions.command.fill")) {
            player.sendMessage(ChatColor.RED + "Insufficient permissions.");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.YELLOW + "Usage: /asyncfill <material>");
            return true;
        }

        Material mat = Material.matchMaterial(args[0].toUpperCase());
        if (mat == null || !mat.isBlock()) {
            player.sendMessage(ChatColor.RED + "Invalid block material.");
            return true;
        }

        int chunkX = player.getLocation().getBlockX() >> 4;
        int chunkZ = player.getLocation().getBlockZ() >> 4;

        blockTask.dispatchChunkFill(player.getWorld(), chunkX, chunkZ, 64, 70, mat);
        player.sendMessage(ChatColor.GREEN + "Dispatched regional chunk fill task on [" + chunkX + ", " + chunkZ + "] with " + mat.name());
        return true;
    }
}
