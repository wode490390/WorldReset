package cn.wode490390.nukkit.worldreset.scheduler;

import cn.nukkit.Server;
import cn.nukkit.level.EnumLevel;
import cn.nukkit.level.GameRules;
import cn.nukkit.level.Level;
import cn.nukkit.level.format.LevelProvider;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.scheduler.PluginTask;
import cn.wode490390.nukkit.worldreset.ResetSetting;
import cn.wode490390.nukkit.worldreset.WorldReset;
import com.google.common.collect.Maps;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ResetTask extends PluginTask<WorldReset> {

    private final ResetSetting setting;
    private final Server server;

    public ResetTask(WorldReset plugin, ResetSetting setting) {
        super(plugin);
        this.setting = setting;
        this.server = plugin.getServer();
    }

    @Override
    public void onRun(int currentTick) {
        String world = this.setting.getWorld();
        Path path = Paths.get(this.server.getDataPath(), "worlds", world);

        Long seed = null;
        GameRules rules = null;

        Class<? extends Generator> generatorClass = null;
        Map<String, Object> options = null;
        Class<? extends LevelProvider> provider = null;

        boolean isDefault = false;

        Level level = this.server.getLevelByName(world);
        if (level != null) {
            if (this.setting.isKeepSeed()) {
                seed = level.getSeed();
            }
            if (this.setting.isKeepRules()) {
                rules = level.getGameRules();
            }

            Generator generator = level.getGenerator();
            generatorClass = generator.getClass();
            options = generator.getSettings();
            provider = level.getProvider().getClass();

            if (this.server.getDefaultLevel() == level) {
                isDefault = true;
            }

            this.server.unloadLevel(level, true);
        } else {
            return; // force reset?
        }

        deleteFileOrDirectory(path.toFile());
        this.server.generateLevel(world, seed != null ? seed : ThreadLocalRandom.current().nextLong(), generatorClass, options != null ? options : Maps.newHashMap(), provider);

        Level newLevel = this.server.getLevelByName(world);
        if (newLevel != null) {
            if (rules != null) {
                newLevel.gameRules = rules;
            }

            if (level == null) {
                this.server.unloadLevel(newLevel);
            } else {
                if (isDefault) {
                    this.server.setDefaultLevel(newLevel);
                }
                if (EnumLevel.OVERWORLD.getLevel() == level || EnumLevel.NETHER.getLevel() == level) {
                    EnumLevel.initLevels();
                }
            }
        }

        this.owner.getLogger().info("World '" + world + "' has been reset");
        this.owner.updateTime(world);
    }

    private static void deleteFileOrDirectory(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null && files.length > 0) {
                    for (File sub : files) {
                        deleteFileOrDirectory(sub);
                    }
                }
            }
            file.delete();
        }
    }
}
