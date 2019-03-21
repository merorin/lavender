package lavender.merorin.dp.decorator.coffee

import lavender.merorin.dp.decorator.Beverage

/**
 * @author bin.guo
 * On 2019-03-21
 */
class Espresso : Beverage {

    override fun getDescription(): String {
        return "Espresso"
    }

    override fun getCost(): Double {
        return 1.99
    }
}