package lavender.merorin.dp.observer

/**
 * @author bin.guo
 * On 2019-03-20
 */
interface Subject<T> {

    fun registerObserver(observer: Observer)

    fun removeObserver(observer: Observer)

    fun notifyObservers()

    fun setChanged()

    fun getData() : T

    fun getName() : String
}