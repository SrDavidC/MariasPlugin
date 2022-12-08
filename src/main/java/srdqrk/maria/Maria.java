package srdqrk.maria;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import srdqrk.maria.listeners.ListenerManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Maria extends JavaPlugin {

    String worldsPath = "worlds";
    private File customConfigFile;
    private FileConfiguration customConfig;
    List<String> whitelistWorlds;
    private ListenerManager listenerManager;
    @Override
    public void onEnable() {
        listenerManager = new ListenerManager(this);
        //Creates config.yml
        // createCustomConfig();
        this.saveDefaultConfig();
        saveConfig();
        // this.saveDefaultConfig();

        // createCustomConfig();
        whitelistWorlds = Arrays.asList(this.getConfig().getString(worldsPath).split(","));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        var customConfigFile = new File(getDataFolder(), "custom.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("custom.yml", false);
            // this.getCustomConfig().set(worldsPath,"");
        }

        var customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        /* User Edit:
            Instead of the above Try/Catch, you can also use
            YamlConfiguration.loadConfiguration(customConfigFile)
        */
    }

    public boolean isWorldWhitelisted(String world) {
        return  (this.whitelistWorlds.contains(world));
    }
}



