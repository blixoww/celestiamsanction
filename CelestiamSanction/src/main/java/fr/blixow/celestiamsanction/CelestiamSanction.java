package fr.blixow.celestiamsanction;

import fr.blixow.celestiamsanction.commands.MuteCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CelestiamSanction extends JavaPlugin {

    private static CelestiamSanction celestiamSanction;

    @Override
    public void onEnable() {
        getCommand("mute").setExecutor(new MuteCommand());
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onLoad() {
        CelestiamSanction.celestiamSanction = this;
    }

    public static CelestiamSanction getCelestiamSanction() {
        return celestiamSanction;
    }

}
