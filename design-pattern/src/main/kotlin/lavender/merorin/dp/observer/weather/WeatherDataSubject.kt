package lavender.merorin.dp.observer.weather

import lavender.merorin.dp.observer.Observer
import lavender.merorin.dp.observer.Subject

/**
 * not thread-safe
 * @author bin.guo
 * On 2019-03-20
 */
class WeatherDataSubject : Subject<WeatherData> {

    private var data : WeatherData? = null

    private val observers : MutableList<Observer> = ArrayList()

    override fun registerObserver(observer: Observer) {
        this.observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        this.observers.remove(observer)
    }

    override fun notifyObservers() {
        this.data?.let {
            this.observers.forEach { observer ->
                @Suppress("UNCHECKED_CAST")
                observer.notify(this as Subject<Any>)
            }
        }?: throw IllegalStateException("Data must be provided before notifying observers!")
    }

    override fun getData() : WeatherData {
        return this.data?: throw IllegalStateException("Data must be provided!")
    }

    fun measurementsChanged(data : WeatherData) {
        this.data = data
        this.notifyObservers()
    }

    override fun getName(): String {
        return "weather-data"
    }
}