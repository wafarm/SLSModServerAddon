package io.github.wafarm.slsmodserveraddon;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import static io.github.wafarm.slsmodserveraddon.SLSModServerAddon.LOGGER;

public class SLSModServerAddonPreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        LOGGER.info("Disabling network handshake for owo-lib");
        System.setProperty("owo.handshake.disable", "true");
    }
}
