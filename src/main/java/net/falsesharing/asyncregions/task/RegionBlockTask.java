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
