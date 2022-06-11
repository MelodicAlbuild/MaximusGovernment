package net.melodicalbuild.maximusgovernment;

import net.melodicalbuild.maximuscore.MaximusCore;
import net.melodicalbuild.maximuscore.plugin.Available.GovernmentPlugin;
import net.melodicalbuild.maximusgovernment.commands.Gov;
import net.melodicalbuild.maximusgovernment.commands.GovBC;
import net.melodicalbuild.maximusgovernment.commands.Vote;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MaximusGovernment extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("govbc")).setExecutor(new GovBC());
        Objects.requireNonNull(this.getCommand("gov")).setExecutor(new Gov());
        Objects.requireNonNull(this.getCommand("vote")).setExecutor(new Vote());

        MaximusCore.pluginManager.EnablePlugin(new GovernmentPlugin("1.0", false));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
