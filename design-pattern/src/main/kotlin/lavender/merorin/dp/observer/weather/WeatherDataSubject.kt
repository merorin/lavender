package lavender.merorin.dp.observer.weather

import lavender.merorin.dp.observer.BaseSubject

/**
 * not thread-safe
 * @author bin.guo
 * On 2019-03-20
 */
class WeatherDataSubject : BaseSubject<WeatherData>() {

    fun measurementsChanged(data : WeatherData) {
        this.setData(data)
        this.notifyObservers()
    }

    override fun getName(): String {
        return "weather-data"
    }
}