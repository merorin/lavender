package lavender.merorin.dp.decorator

import lavender.merorin.dp.decorator.coffee.DarkRoast
import lavender.merorin.dp.decorator.coffee.Espresso
import lavender.merorin.dp.decorator.coffee.HouseBlend
import lavender.merorin.dp.decorator.condiment.Mocha
import lavender.merorin.dp.decorator.condiment.Soy
import lavender.merorin.dp.decorator.condiment.Whip
import java.util.*

/**
 * @author bin.guo
 * On 2019-03-21
 */
object StarbuzzCoffeeMain {

    @JvmStatic
    fun main(args : Array<String>) {
        Espresso().apply {
            println(this.text())
        }

        Optional.of(DarkRoast())
                .map { Mocha(it) }
                .map { Mocha(it) }
                .map { Whip(it) }
                .ifPresent { println(it.text()) }

        Optional.of(HouseBlend())
                .map { Soy(it) }
                .map { Mocha(it) }
                .map { Whip(it) }
                .ifPresent { println(it.text()) }
    }

    private fun Beverage.text() : String {
        return "${this.getDescription()} $ ${this.getCost()}"
    }
}