package lavender.merorin.dp.decorator.coffee

import lavender.merorin.dp.decorator.Beverage

/**
 * @author bin.guo
 * On 2019-03-21
 */
class HouseBlend : Beverage {

    override fun getDescription(): String {
        return "HouseBlend"
    }

    override fun getCost(): Double {
        return 0.89
    }
}