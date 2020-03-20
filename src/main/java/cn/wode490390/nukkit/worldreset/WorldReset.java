package cn.wode490390.nukkit.worldreset;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.wode490390.nukkit.worldreset.command.ResetCommand;
import cn.wode490390.nukkit.worldreset.scheduler.ResetData;
import cn.wode490390.nukkit.worldreset.scheduler.ResetTrigger;
import cn.wode490390.nukkit.worldreset.util.MetricsLite;

import java.io.File;
import java.util.Date;
import java.util.Timer;

public class WorldReset extends PluginBase {

    private Timer timer;
    private ResetData data;

    @Override
    public void onEnable() {
        try {
            new MetricsLite(this, 6811);
        } catch (Throwable ignore) {

        }

        this.data = new ResetData(new Config(new File(this.getDataFolder(), "data.yml"), Config.YAML));
        this.saveDefaultConfig();
        Config config = this.getConfig();

        String node = "worlds";
        ConfigSection section = config.getSections(node);
        if (!section.isEmpty()) {
            this.timer = new Timer(true);

            for (String world : section.getKeys(false)) {
                String subNode = "worlds." + world;

                long period = 10080;
                node = subNode + ".period";
                try {
                    period = Math.max(config.getLong(node, period), 1);
                } catch (Exception ignore) {

                }
                period *= 1000 * 60;

                boolean keepSeed = false;
                node = subNode + ".keep-seed";
                try {
                    keepSeed = config.getBoolean(node, keepSeed);
                } catch (Exception ignore) {

                }

                boolean keepRules = true;
                node = subNode + ".keep-gamerule";
                try {
                    keepRules = config.getBoolean(node, keepRules);
                } catch (Exception ignore) {

                }

                this.timer.schedule(new ResetTrigger(this, new ResetSetting(world, keepSeed, keepRules)), new Date(this.data.getLast(world) + period), period);
            }

            this.getServer().getScheduler().scheduleRepeatingTask(this, this.data, 20 * 60 * 1, true);
        }

        this.getServer().getCommandMap().register("worldreset", new ResetCommand(this));
    }

    @Override
    public void onDisable() {
        this.data.save();
        this.timer.cancel();
    }

    public void updateTime(String world) {
        this.data.updateTime(world);
    }
}
