package lavender.merorin.dp.observer.weather

import lavender.merorin.dp.observer.BaseObserver
import lavender.merorin.dp.observer.Subject

/**
 * @author bin.guo
 * On 2019-03-21
 */
class CurrentConditionDisplay: BaseObserver(), Displayable {

    private var message : String = "No data for ${this.javaClass.simpleName}"

    override fun doNotify(subject: Subject<Any>) {
        this.message = "Current condition....${subject.getData()}"
    }

    override fun display() {
        message.apply { println(this) }
    }
}