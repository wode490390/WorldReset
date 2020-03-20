package cn.wode490390.nukkit.worldreset;

public class ResetSetting {

    private final String world;
    private final boolean keepSeed;
    private final boolean keepRules;

    public ResetSetting(String world, boolean keepSeed, boolean keepRules) {
        this.world = world;
        this.keepSeed = keepSeed;
        this.keepRules = keepRules;
    }

    public String getWorld() {
        return world;
    }

    public boolean isKeepSeed() {
        return keepSeed;
    }

    public boolean isKeepRules() {
        return keepRules;
    }

    @Override
    public String toString() {
        return "ResetSetting(world=" + world + ", keepSeed=" + keepSeed + ", keepRules=" + keepRules + ")";
    }
}
