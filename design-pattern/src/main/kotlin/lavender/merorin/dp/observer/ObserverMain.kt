package lavender.merorin.dp.observer

import lavender.merorin.dp.observer.weather.CurrentConditionDisplay
import lavender.merorin.dp.observer.weather.Displayable
import lavender.merorin.dp.observer.weather.ForecastDisplay
import lavender.merorin.dp.observer.weather.StaticDisplay
import lavender.merorin.dp.observer.weather.WeatherData
import lavender.merorin.dp.observer.weather.WeatherDataSubject

/**
 * @author bin.guo
 * On 2019-03-21
 */
object ObserverMain {

    @JvmStatic
    fun main(args : Array<String>) {
        val subject = WeatherDataSubject()
        val displays : List<Displayable> = listOf(CurrentConditionDisplay(), ForecastDisplay(), StaticDisplay())

        for (i in 1..5) {
            when (i) {
                1, 2, 3 -> subject.registerObserver(displays[i - 1] as Observer)
                4 -> subject.removeObserver(displays[0] as Observer)
                5 -> subject.removeObserver(displays[2] as Observer)
            }
            subject.measurementsChanged(WeatherData((0.1 * i).toFloat(), (0.3 * i).toFloat(), (0.5 * i).toFloat()))
            displays.forEach { it.display() }
        }
    }
}