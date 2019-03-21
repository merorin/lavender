package lavender.merorin.dp.observer

import kotlin.collections.HashMap

/**
 * @author bin.guo
 * On 2019-03-21
 */
abstract class BaseObserver : Observer {

    private var subjects: MutableMap<String, Subject<Any>> = HashMap()

    override fun notify(subject: Subject<Any>) {
        subjects[subject.getName()] = subject
        doNotify(subject)
    }

    protected abstract fun doNotify(subject: Subject<Any>)

    protected fun doWithSubject(name: String, consumer: (Subject<Any>) -> Unit) {
        consumer(subjects[name]?: throw IllegalStateException("Subject not found!"))
    }
}