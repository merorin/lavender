package lavender.merorin.dp.command

import lavender.merorin.dp.command.Command
import lavender.merorin.dp.command.appliance.Stereo

/**
 * @author bin.guo
 * On 2019-03-26
 */
class StereoOnWithCdCommand(
        private val stereo: Stereo
) : Command {

    override fun execute() {
        stereo.on()
        stereo.setCd()
        stereo.setVolume(11)
    }
}