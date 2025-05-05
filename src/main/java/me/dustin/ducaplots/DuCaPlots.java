package me.dustin.ducaplots;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.WorldCreator;
import me.dustin.ducaplots.generator.PlotWorldGenerator;

public class DuCaPlots extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DuCaPlots geladen – von Dustin");
    }

    @Override
    public void onDisable() {
        getLogger().info("DuCaPlots deaktiviert.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("p")) {
            if (args.length >= 2 && args[0].equalsIgnoreCase("world") && args[1].equalsIgnoreCase("create")) {
                WorldCreator creator = new WorldCreator("plots");
                creator.generator(new PlotWorldGenerator());
                Bukkit.createWorld(creator);
                sender.sendMessage("Plot-Welt 'plots' wurde erstellt.");
                return true;
            }

            sender.sendMessage("DuCaPlots – Verfügbar: /p world create, /p merge (bald)");
            return true;
        }
        return false;
    }
}
