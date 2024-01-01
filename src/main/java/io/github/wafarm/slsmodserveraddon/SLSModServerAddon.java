package io.github.wafarm.slsmodserveraddon;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class SLSModServerAddon implements ModInitializer {
    public static final String MOD_NAME = "SLSModServerAddon";
    public static Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        LOGGER.info("Creating a detailed report of everything...");
        collectSystemInfo();

        // I don't like PCL for various reasons
        if (Minecraft.getInstance().getVersionType().contains("PCL")) {
            LOGGER.warn("Stop using PCL!");
        }
    }

    private void collectSystemInfo() {
        SystemInfo info = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = info.getHardware();

        LOGGER.info("Operating system: {}", System.getProperty("os.name", "generic"));
        LOGGER.info("CPU: {}", hardwareAbstractionLayer.getProcessor().getProcessorIdentifier().getName());
        LOGGER.info("Java runtime architecture: {}", System.getProperty("os.arch"));
        LOGGER.info("Java runtime: {} {} {}", System.getProperty("java.vm.vendor"), System.getProperty("java.vm.name"), System.getProperty("java.vm.version"));

        long maxMemory = Runtime.getRuntime().maxMemory();
        long allocatedMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = allocatedMemory - freeMemory;
        LOGGER.info("Java memory (used/max): {} / {} MB", usedMemory / 1048576, maxMemory / 1048576);

        long systemTotalMemory = hardwareAbstractionLayer.getMemory().getTotal();
        long systemFreeMemory = hardwareAbstractionLayer.getMemory().getAvailable();
        long systemUsedMemory = systemTotalMemory - systemFreeMemory;
        LOGGER.info("System memory (used/total): {} / {} MB", systemUsedMemory / 1048576, systemTotalMemory / 1048576);
    }
}
