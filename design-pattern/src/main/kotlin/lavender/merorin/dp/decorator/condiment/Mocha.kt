package lavender.merorin.dp.decorator.condiment

import lavender.merorin.dp.decorator.Beverage

/**
 * @author bin.guo
 * On 2019-03-21
 */
class Mocha(beverage : Beverage) : CondimentDecorator(beverage) {

    override fun getDescription(): String {
        return "${beverage.getDescription()}, Mocha"
    }

    override fun getCost(): Double {
        return beverage.getCost() + 0.2
    }
}