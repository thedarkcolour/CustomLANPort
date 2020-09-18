package thedarkcolour.customlanport.config

import net.minecraftforge.common.ForgeConfigSpec

object Config {
    var port: Int = 0

    fun refresh() {
        port = ConfigHolder.CFG.portCfg.get()
    }
}

object ConfigHolder {
    val CFG: _Internal
    val SPEC: ForgeConfigSpec

    init {
        val specPair = ForgeConfigSpec.Builder().configure(::_Internal)
        CFG = specPair.left
        SPEC = specPair.right
    }
}

class _Internal(builder: ForgeConfigSpec.Builder) {
    val portCfg: ForgeConfigSpec.IntValue = builder
            .comment("Choose the LAN port.")
            .translation("customlanport.config.port")
            .defineInRange("port", 25565, 0, 65535)
}