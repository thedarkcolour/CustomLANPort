package thedarkcolour.customlanport.config

import net.minecraftforge.common.ForgeConfigSpec

object Config {
    var port: Int = 0

    fun refresh() {
        port = ConfigHolder.COMMON.portCfg.get()
    }
}

object ConfigHolder {
    val COMMON: CommonConfig
    val SPEC: ForgeConfigSpec

    init {
        val specPair = ForgeConfigSpec.Builder().configure(::CommonConfig)
        COMMON = specPair.left
        SPEC = specPair.right
    }
}

class CommonConfig(builder: ForgeConfigSpec.Builder) {
    val portCfg: ForgeConfigSpec.IntValue = builder
            .comment("Choose the LAN port.")
            .translation("customlanport.config.port")
            .defineInRange("port", 25565, 0, 65535)
}