package lavender.merorin.dp.command

import lavender.merorin.dp.command.appliance.Light

/**
 * @author bin.guo
 * On 2019-03-26
 */
class LightOffCommand(
        private val light: Light
) : Command {

    override fun execute() {
        light.off()
    }
}