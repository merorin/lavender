package lavender.merorin.dp.command

import lavender.merorin.dp.command.appliance.GarageDoor
import lavender.merorin.dp.command.appliance.Light

/**
 * @author bin.guo
 * On 2019-03-26
 */
object RemoteControllerTest {

    @JvmStatic
    fun main(args : Array<String>) {
        val controller = SimpleRemoteController()
        LightOnCommand(Light()).apply {
            controller.setCommand(this)
        }
        controller.buttonWasPressed()

        GarageDoorOpenCommand(GarageDoor()).apply {
            controller.setCommand(this)
        }
        controller.buttonWasPressed()
    }
}