package lavender.merorin.dp.decorator.coffee

import lavender.merorin.dp.decorator.Beverage

/**
 * @author bin.guo
 * On 2019-03-21
 */
class DarkRoast : Beverage {

    override fun getDescription(): String {
        return "DarkRoast"
    }

    override fun getCost(): Double {
        return 0.99
    }
}