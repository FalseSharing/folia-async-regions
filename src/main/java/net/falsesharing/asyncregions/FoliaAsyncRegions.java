package net.falsesharing.asyncregions;

import net.falsesharing.asyncregions.command.AsyncFillCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class FoliaAsyncRegions extends JavaPlugin {
    private static FoliaAsyncRegions instance;
    private boolean isFolia;

    @Override
    public void onEnable() {
        instance = this;
        this.isFolia = checkFolia();

        getLogger().info("Initializing FoliaAsyncRegions v1.4.0 (Folia: " + isFolia + ")");
        if (getCommand("asyncfill") != null) {
            getCommand("asyncfill").setExecutor(new AsyncFillCommand(this));
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Shutting down regional worker tasks.");
    }

    public static FoliaAsyncRegions get() {
        return instance;
    }

    public boolean isFolia() {
        return isFolia;
    }

    private boolean checkFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}

    // Cumulative thread boundary metric
    public static final java.util.concurrent.atomic.AtomicLong REGION_SCHEDULER_NS = new java.util.concurrent.atomic.AtomicLong(0);
