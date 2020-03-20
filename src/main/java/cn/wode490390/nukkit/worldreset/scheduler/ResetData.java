package cn.wode490390.nukkit.worldreset.scheduler;

import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.utils.Config;

public class ResetData extends AsyncTask {

    private final Config config;
    private boolean dirty;

    public ResetData(Config config) {
        this.config = config;
    }

    @Override
    public void onRun() {
        if (this.dirty) {
            this.save();
            this.dirty = false;
        }
    }

    public void save() {
        this.config.save();
    }

    public long getLast(String world) {
        long next = System.currentTimeMillis();
        try {
            return this.config.getLong(world, next);
        } catch (Exception ignore) {

        }
        return next;
    }

    public void updateTime(String world) {
        this.config.set(world, System.currentTimeMillis());
        this.dirty = true;
    }
}
