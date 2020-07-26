package thedarkcolour.customlanport

import net.minecraft.client.gui.screen.ShareToLanScreen
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import thedarkcolour.customlanport.config.Config
import thedarkcolour.customlanport.config.ConfigHolder
import thedarkcolour.customlanport.screen.CustomShareToLanScreen
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.registerConfig

@Mod("customlanport")
object CustomLanPort {
    init {
        FORGE_BUS.addListener(::onLanGui)
        MOD_BUS.addListener(::onModConfig)
        registerConfig(ModConfig.Type.COMMON, ConfigHolder.SPEC, "customlanport.toml")
    }

    private fun onModConfig(event: ModConfig.ModConfigEvent) {
        Config.port = event.config.configData["port"]
    }

    private fun onLanGui(event: GuiOpenEvent) {
        val screen = event.gui
        if (screen is ShareToLanScreen) {
            event.gui = CustomShareToLanScreen(screen)
        }
    }
}