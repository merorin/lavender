package lavender.merorin.dp.observer

/**
 * not thread-safe
 * @author bin.guo
 * On 2019-03-21
 */
abstract class BaseSubject<T> : Subject<T> {

    private var data : T? = null

    private val observers : MutableList<Observer> = ArrayList()

    @Volatile private var changed : Boolean = false

    override fun registerObserver(observer: Observer) {
        this.observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        this.observers.remove(observer)
    }

    override fun notifyObservers() {
        if (!changed) {
            return
        }
        this.data?.let {
            this.observers.forEach { observer ->
                @Suppress("UNCHECKED_CAST")
                observer.notify(this as Subject<Any>)
            }
        }?: throw IllegalStateException("Data must be provided before notifying observers!")
    }

    override fun setChanged() {
        changed = true
    }

    override fun getData() : T {
        return this.data?: throw IllegalStateException("Data must be provided!")
    }

    protected fun setData(data : T) {
        this.data = data
    }
}