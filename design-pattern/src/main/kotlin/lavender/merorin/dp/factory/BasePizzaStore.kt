package lavender.merorin.dp.factory

/**
 * @author bin.guo
 * On 2019-03-25
 */
abstract class BasePizzaStore {

    fun orderPizza(type : String) : Pizza {
        return createPizza(type)
                .also { it.prepare() }
                .also { it.bake() }
                .also { it.cut() }
                .also { it.box() }
    }


    protected abstract fun createPizza(type: String) : Pizza
}