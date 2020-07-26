package thedarkcolour.customlanport.screen

import net.minecraft.client.gui.screen.ShareToLanScreen
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.client.resources.I18n
import net.minecraft.util.text.TranslationTextComponent
import net.minecraft.world.GameType
import thedarkcolour.customlanport.config.Config

class CustomShareToLanScreen(toReplace: ShareToLanScreen) : ShareToLanScreen(toReplace.lastScreen) {
    private lateinit var startGameButton: Button

    override fun init() {
        super.init()
        removeOpenButton()
        startGameButton = addButton(Button(width / 2 - 155, height - 28, 150, 20, TranslationTextComponent("lanServer.start"), ::openToLan))
    }

    /**
     * Removes the original Open to Lan button so we can replace its functionality.
     */
    private fun removeOpenButton() {
        buttons.removeIf {
            it.message == TranslationTextComponent("lanServer.start")
        }
        children.removeIf {
            if (it is Button) {
                it.message == TranslationTextComponent("lanServer.start")
            } else {
                false
            }
        }
    }

    /**
     * Opens the world to Lan.
     */
    private fun openToLan(button: Button) {
        val i = Config.port
        minecraft!!.displayGuiScreen(null)
        if (minecraft!!.integratedServer!!.shareToLAN(GameType.getByName(gameMode), allowCheats, i)) {
            minecraft!!.ingameGUI.chatGUI.printChatMessage(TranslationTextComponent("commands.publish.started", i))
        } else {
            minecraft!!.ingameGUI.chatGUI.printChatMessage(TranslationTextComponent("commands.publish.failed", i))
        }
    }
}