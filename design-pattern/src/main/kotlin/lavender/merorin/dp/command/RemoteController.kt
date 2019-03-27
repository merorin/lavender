package lavender.merorin.dp.command

import java.lang.StringBuilder

/**
 * @author bin.guo
 * On 2019-03-26
 */
class RemoteController {

    private val onCommands : Array<Command> = newArray()
    private val offCommands : Array<Command> = newArray()

    companion object {
        private const val TOTAL_SLOTS : Int = 7
        private val UNDEFINED_COMMAND = object : Command {
            override fun execute() { }
        }
    }

    fun setCommand(slot : Int, onCommand : Command, offCommand : Command) {
        onCommands[slot] = onCommand
        offCommands[slot] = offCommand
    }

    fun onButtonWasPressed(slot: Int) {
        onCommands[slot].execute()
    }

    fun offButtonWasPressed(slot: Int) {
        offCommands[slot].execute()
    }

    private fun newArray() : Array<Command> {
        return Array(TOTAL_SLOTS) { UNDEFINED_COMMAND }
    }

    override fun toString(): String {
        return StringBuilder("\n--------- Remote Controller --------\n")
                .append(onCommands.buildString())
                .append(offCommands.buildString())
                .toString()
    }

    private fun Array<Command>.buildString() : String {
        return this.joinToString(",", "[", "]") { it.javaClass.name }
    }
}