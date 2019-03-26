package lavender.merorin.dp.singleton

/**
 * @author bin.guo
 * On 2019-03-26
 */
class Singleton private constructor() {

    init {
        println("let us go.")
    }



    companion object {

        private val singletonInstance by lazy {
            println("Initializing single object....")
            Singleton()
        }


        @JvmStatic
        fun getInstance() : Singleton {
            println("begin to access")
            return singletonInstance
        }

        @JvmStatic
        fun main(args : Array<String>) {
            println("begin.")
            println(Singleton.getInstance())
            println(Singleton.getInstance())
        }
    }
}