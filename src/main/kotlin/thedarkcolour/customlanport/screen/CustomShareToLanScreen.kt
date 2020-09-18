package thedarkcolour.customlanport.screen

import net.minecraft.client.gui.screen.ShareToLanScreen
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.util.text.TranslationTextComponent
import net.minecraft.world.GameType
import thedarkcolour.customlanport.config.Config

class CustomShareToLanScreen(toReplace: ShareToLanScreen) : ShareToLanScreen(toReplace.lastScreen) {
    //private lateinit var chosenPortWidget: NumberWidget
    private lateinit var startGameButton: Button

    override fun init() {
        super.init()
        val startMsg = TranslationTextComponent("lanServer.start")

        buttons.removeIf {
            startMsg == it.message
        }
        children.removeIf {
            if (it is Button) {
                startMsg == it.message
            } else {
                false
            }
        }
        startGameButton = addButton(Button(
                width / 2 - 155,
                height - 28, 150, 20, startMsg) {
            val i = Config.port
            minecraft!!.displayGuiScreen(null)
            if (minecraft!!.integratedServer!!.shareToLAN(GameType.getByName(gameMode), allowCheats, i)) {
                minecraft!!.ingameGUI.chatGUI.printChatMessage(TranslationTextComponent("commands.publish.started", i))
            } else {
                minecraft!!.ingameGUI.chatGUI.printChatMessage(TranslationTextComponent("commands.publish.failed", i))
            }
        })
    }
/*
    class NumberWidget(gui: CustomShareToLanScreen) : TextFieldWidget(gui.font, gui.width / 2 - 155, gui.height - 28, 100, 20, "25565") {
        init {
            setMaxStringLength(5)
        }

        override fun writeText(msg: String) {
            if (!StringUtils.isNumeric(msg)) {
                return
            }
            super.writeText(msg)

            //try {
            //    Minecraft.getInstance().integratedServer!!.networkSystem!!.addEndpoint(null, gui.chosenPortWidget.port)
            //} catch (e: IOException) {
            //    setTextColor(0xAA0000)
            //    gui.startGameButton.active = false
            //    return
            //} catch (e: NullPointerException) {
            //    setTextColor(0xAA0000)
            //    return
            //}
            //Minecraft.getInstance().integratedServer!!.networkSystem!!.
        }

        val port: Int
            get() = Integer.parseInt(message)
    }*/
}