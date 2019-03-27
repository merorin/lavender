package lavender.merorin.dp.command

/**
 * @author bin.guo
 * On 2019-03-26
 */
class SimpleRemoteController {

    private var command : Command? = null

    fun setCommand(command: Command) {
        this.command = command
    }

    fun buttonWasPressed() {
        this.command?.execute() ?: throw IllegalStateException("No command found.")
    }
}