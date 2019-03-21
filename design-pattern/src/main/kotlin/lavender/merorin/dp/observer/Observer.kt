package lavender.merorin.dp.observer

/**
 * @author bin.guo
 * On 2019-03-20
 */
interface Observer {

    fun notify(subject : Subject<Any>)
}