package lavender.merorin.dp.decorator.coffee

import lavender.merorin.dp.decorator.Beverage

/**
 * @author bin.guo
 * On 2019-03-21
 */
class Decafeineited : Beverage {

    override fun getDescription(): String {
        return "Decafeineited"
    }

    override fun getCost(): Double {
        return 1.05
    }
}