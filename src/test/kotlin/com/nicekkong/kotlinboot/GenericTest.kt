package com.nicekkong.kotlinboot


import org.apache.coyote.http11.Constants.a
import org.junit.jupiter.api.Test
import kotlin.math.pow

class GenericTest {


    @Test
    fun testtest() {
        println("Asdfasdfasd")
    }

    @Test
    fun sort() {
        val numList = mutableListOf(3, 2, 1)
        numList.sort()
        val descendingList = numList.sorted()

        numList.forEach{
            println(it)
        }

        numList.forEachIndexed{index, v ->
            println("$index ===> $v")
            println("$index ===> ${v*2}")
            println("$index ===> ${v+100}")
        }

        val filter = numList.filter { it >= 2 }
        println(filter)


        descendingList.map{ it + 100 }

        val list = mutableListOf(1 to "A", 2 to "B", 100 to "C", 50 to "D", 10 to "E")
        list.sortByDescending { it.second }
    }




}


@Test
fun slice_test() {

    ('a'..'z').toList().slice<Char>(0..2)
}

fun <T> List<T>.slice(indices:IntRange):List<T> = this.slice(indices)


fun <T, L, C> addTL(a:T, b:L): C {
    return (a.toString() + b.toString()) as C
}


interface List<T> {
    operator fun get(index:Int):T
}

class StringList:List<String> {
    override fun get(index:Int): String = this[index]
}

infix fun Int.`**`(x:Int) = toDouble().pow(x).toInt()

fun powInt() = 5 `**` 1

fun mapTo() {

    val pairs:Pair<Int, Int> = 1 to 2
    val pairs2:Map<Int, Int> = mapOf(1 to 2, 2 to 3)

}

class Task(val name:String) {
    constructor() : this(name = "1")

    var priority = 3
        get() = field + 2
        set(value) {
            field = value + 3
        }
}


fun testTask() {
    var myTask = Task().apply {
//        name = "123"
        priority = 5
    }

}


class General<out T: Number>(val size:T) {

    fun getSquare():T = (size.toDouble() * size.toDouble()) as T

}


val g1 = General(1.2323F)
val g2 = General(100000L)
val g3 = General(100)
//val g_error = General("adfasfads")
