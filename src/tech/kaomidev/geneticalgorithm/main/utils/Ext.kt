@file:JvmName("Utils")
package tech.kaomidev.geneticalgorithm.main.utils

// Converts  '0' and '1' to their representation in bytes
fun Char.parseByte() = this.toString().toByte()

// To make it easier we can use this extension method
// to set our candidate solution with string of 0s and 1s
fun String.parseByteArray(): ByteArray {
    return map { char ->
        when(char) {
            '0', '1' -> char.parseByte()
            else -> 0
        }
    }.toByteArray()
}
