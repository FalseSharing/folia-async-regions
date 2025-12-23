package net.falsesharing.asyncregions.task;

import net.falsesharing.asyncregions.FoliaAsyncRegions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public final class RegionBlockTask {
    private final FoliaAsyncRegions plugin;

    public RegionBlockTask(FoliaAsyncRegions plugin) {
        this.plugin = plugin;
    }

    public void dispatchChunkFill(World world, int chunkX, int chunkZ, int minY, int maxY, Material material) {
        Runnable blockWork = () -> {
            int startX = chunkX << 4;
            int startZ = chunkZ << 4;
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    for (int y = minY; y <= maxY; ++y) {
                        Block block = world.getBlockAt(startX + x, y, startZ + z);
                        if (block.getType() != material) {
                            block.setType(material, false);
                        }
                    }
                }
            }
        };

        if (plugin.isFolia()) {
            Bukkit.getRegionScheduler().execute(plugin, world, chunkX, chunkZ, blockWork);
        } else {
            Bukkit.getScheduler().runTask(plugin, blockWork);
        }
    }
}
// rev 1 [2023-09-26 17:06:28 +0300]: region check
// rev 2 [2023-10-02 18:38:48 +0300]: region check
// rev 3 [2023-10-05 10:55:19 +0300]: region check
// rev 4 [2023-10-19 20:03:57 +0300]: region check
// rev 5 [2023-10-22 15:08:38 +0300]: region check
// rev 6 [2023-11-27 12:50:12 +0300]: region check
// rev 7 [2024-03-06 09:02:11 +0300]: region check
// rev 8 [2024-05-16 13:14:04 +0300]: region check
// rev 9 [2024-05-31 19:51:18 +0300]: region check
// rev 10 [2024-08-10 13:44:50 +0300]: region check
// rev 11 [2024-08-12 10:30:25 +0300]: region check
// rev 12 [2024-08-31 18:19:02 +0300]: region check
// rev 13 [2024-10-25 10:33:31 +0300]: region check
// rev 14 [2024-11-16 11:16:23 +0300]: region check
// rev 15 [2024-12-10 16:24:15 +0300]: region check
// rev 16 [2025-03-15 12:33:14 +0300]: region check
// rev 17 [2025-04-06 17:04:55 +0300]: region check
// rev 18 [2025-04-13 20:31:47 +0300]: region check
// rev 19 [2025-04-17 13:00:01 +0300]: region check
// rev 20 [2025-06-06 19:11:00 +0300]: region check
// rev 21 [2025-06-16 20:00:50 +0300]: region check
// rev 22 [2025-06-30 20:00:09 +0300]: region check
// rev 23 [2025-08-06 09:31:42 +0300]: region check
// rev 24 [2025-08-10 09:34:51 +0300]: region check
// rev 25 [2025-09-27 20:22:27 +0300]: region check
// rev 26 [2025-09-29 22:44:17 +0300]: region check
// rev 27 [2025-12-23 13:43:07 +0300]: region check
