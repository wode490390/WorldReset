package cn.wode490390.nukkit.worldreset.scheduler;

import cn.nukkit.Server;
import cn.wode490390.nukkit.worldreset.ResetSetting;
import cn.wode490390.nukkit.worldreset.WorldReset;

import java.util.TimerTask;

public class ResetTrigger extends TimerTask {

    private final WorldReset plugin;
    private final ResetSetting setting;

    public ResetTrigger(WorldReset plugin, ResetSetting setting) {
        this.plugin = plugin;
        this.setting = setting;
    }

    @Override
    public void run() {
        Server.getInstance().getScheduler().scheduleTask(this.plugin, new ResetTask(this.plugin, this.setting));
    }
}
