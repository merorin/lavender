package lavender.merorin.dp.decorator.condiment

import lavender.merorin.dp.decorator.Beverage

/**
 * @author bin.guo
 * On 2019-03-21
 */
class Soy(beverage: Beverage) : CondimentDecorator(beverage) {

    override fun getDescription(): String {
        return "${beverage.getDescription()}, Soy"
    }

    override fun getCost(): Double {
        return beverage.getCost() + 0.15
    }
}