package lavender.merorin.dp.command

import lavender.merorin.dp.command.appliance.GarageDoor

/**
 * @author bin.guo
 * On 2019-03-26
 */
class GarageDoorOpenCommand(
        private val garageDoor: GarageDoor
) : Command {

    override fun execute() {
        garageDoor.up()
    }
}