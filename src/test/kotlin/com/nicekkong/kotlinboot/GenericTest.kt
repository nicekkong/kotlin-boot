package com.nicekkong.kotlinboot

import org.junit.jupiter.api.Test
import kotlin.math.pow

class GenericTest


@Test
fun slice_test() {

    ('a'..'z').toList().slice<Char>(0..2)
}

fun <T> List<T>.slice(indices:IntRange):List<T> = this.slice(indices)


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