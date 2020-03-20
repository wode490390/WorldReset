package cn.wode490390.nukkit.worldreset.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginIdentifiableCommand;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.plugin.Plugin;
import cn.wode490390.nukkit.worldreset.ResetSetting;
import cn.wode490390.nukkit.worldreset.scheduler.ResetTask;
import cn.wode490390.nukkit.worldreset.WorldReset;

import java.util.Arrays;

public class ResetCommand extends Command implements PluginIdentifiableCommand {

    private final WorldReset plugin;

    public ResetCommand(WorldReset plugin) {
        super("worldreset", "Resets a world.", "/worldreset <levelName: String> [keepSeed: Boolean] [keepRules: Boolean]", new String[]{"wr"});
        this.setPermission("worldreset.command");
        this.getCommandParameters().clear();
        this.addCommandParameters("default", new CommandParameter[]{
                new CommandParameter("levelName", CommandParamType.STRING, false),
                new CommandParameter("keepSeed", true, "Boolean") {
                    {
                        this.enumData.getValues().addAll(Arrays.asList("true", "false"));
                    }
                },
                new CommandParameter("keepRules", true, "Boolean") {
                    {
                        this.enumData.getValues().addAll(Arrays.asList("true", "false"));
                    }
                }
        });
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.plugin.isEnabled() || !this.testPermission(sender)) {
            return false;
        }

        if (args.length > 0) {
            String world = args[0];
            boolean keepSeed = false;
            boolean keepRules = true;

            if (args.length > 1) {
                keepSeed = Boolean.parseBoolean(args[1]);
                if (args.length > 2) {
                    keepRules = Boolean.parseBoolean(args[2]);
                }
            }

            this.plugin.getServer().getScheduler().scheduleTask(this.plugin, new ResetTask(this.plugin, new ResetSetting(world, keepSeed, keepRules)));
        } else {
            sender.sendMessage(new TranslationContainer("commands.generic.usage", this.getUsage()));
        }

        return true;
    }

    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }
}
