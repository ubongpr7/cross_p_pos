package org.akwapos.app.utils


import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.round

fun Long.bytesToMB(): Double {
    return this / (1024.0 * 1024.0)
}

fun Number.isBetween(start: Number, end: Number): Boolean {
    return when (this) {
        is Float -> {
            this >= start.toFloat() && this <= end.toFloat()
        }

        is Double -> {
            this >= start.toDouble() && this <= end.toDouble()
        }

        is Int -> {
            this >= start.toInt() && this <= end.toInt()
        }

        else -> throw NumberFormatException("Unsupported Number type for comparison")
    }
}

/**
 * Executes [action] with the non-null value if [value] is not null and returns a null value
 */
inline fun <A : Any, B> A?.getIfNotNull(default: B, action: (A) -> B): B {
    return if (this != null) action(this)
    else default
}

/**
 * Executes [action] with the non-null value if [value] is not null
 */
inline fun <T : Any> T?.ifNotNull(action: (T) -> Unit): T? {
    if (this != null) action(this)
    return this
}

/**
 * Executes [action] if [value] is null
 */
inline fun <T : Any> T?.ifNull(action: () -> Unit): T? {
    if (this == null) action()
    return this
}

/** Executes [action] with the receiver object and returns the result. */
inline fun <A, B> A.mapper(action: (A) -> B): B = action(this)

/**
 * Calculates the ratio of two parts relative to a whole number.
 *
 * This function divides the whole number based on the ratio defined by `part1` and `part2`.
 * It ensures that both parts are not zero to avoid division by zero and
 * uses integer arithmetic for efficiency when possible.
 *
 * @param part1 The first part of the ratio.
 * @param part2 The second part of the ratio.
 * @return A [Pair] where the first element is the calculated value for `part1`,
 *         and the second element is the calculated value for `part2`.
 * @throws IllegalArgumentException if both `part1` and `part2` are zero.
 */
fun Int.calculateRatio(
    part1: Int,
    part2: Int
): Pair<Int, Int> {
    val sum = part1 + part2
    if (sum == 0) throw IllegalArgumentException("Parts must not both be zero") // Check for division by zero
    val ratio1 = (part1 * this) / sum // using int operation and avoiding double calculation
    val ratio2 = this - ratio1 // optimizing calculations


    return Pair(ratio1, ratio2)
}

/**
 * Converts a value from degrees to radians using the mathematical formula.
 *
 * @param degrees The angle value in degrees.
 * @return The angle value converted to radians.
 */
fun Double.toRadians(): Double {
    // The formula is degrees * (PI / 180)
    return this * (PI / 180.0)
}

/**
 * Converts a value from radians to degrees using the mathematical formula.
 *
 * @param radians The angle value in radians.
 * @return The angle value converted to degrees.
 */
fun Double.toDegrees(): Double {
    // The formula is radians * (180 / PI)
    return this * (180.0 / PI)
}


/**
 * Rounds the number to a specified number of decimal places.
 *
 * This extension function works for `Float`, `Double`, and integer types (`Int`, `Long`, `Short`, `Byte`).
 * For integer types, the original number is returned as no rounding is necessary.
 *
 * @param decimalPlaces The number of decimal places to round to. Must be non-negative.
 * @return The rounded number of the same type as the original number.
 * @throws IllegalArgumentException if `decimalPlaces` is negative.
 * @throws UnsupportedOperationException if the number type is not supported.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T : Number> T.roundToPlaces(decimalPlaces: Int): T {
    return when (this) {
        is Float -> {
            require(decimalPlaces >= 0) { "Decimal places must be non-negative" }
            val factor = 10f.pow(decimalPlaces)
            (round(this * factor) / factor) as T
        }

        is Double -> {
            require(decimalPlaces >= 0) { "Decimal places must be non-negative" }
            val factor = 10.0.pow(decimalPlaces)
            (round(this * factor) / factor) as T
        }

        is Int, is Long, is Short, is Byte -> this // Whole numbers don't need rounding
        else -> throw UnsupportedOperationException("Unsupported Number type: ${this::class.simpleName}")
    }
}


/**
 * Cleans potential escape sequences and extraneous whitespace from a JSON string.
 *
 * This function performs the following replacements:
 * - Removes `\r\n` sequences.
 * - Removes `\n` sequences.
 * - Replaces `\"` with a literal double quote `"`.
 *
 * After performing the replacements, the function also trims leading and trailing whitespace
 * from the resulting string.
 *
 * This can be useful when dealing with JSON strings that have been improperly serialized
 * or contain unwanted escape characters or formatting.
 *
 * @return The cleaned JSON string.
 */
fun String.cleanJson(): String {
    return this
        .replace("\\r\\n", "")
        .replace("\\n", "")
        .replace("\\\"", "\"")
        .trim()
}


/**
 * Calculates a percentage of the current number.
 *
 * This is an extension function for any type that is a subclass of [Number].
 * It takes another number representing the percentage and returns the calculated
 * percentage value, maintaining the original number type.
 *
 * Supported number types are [Int], [Double], [Float], [Long], [Short], and [Byte].
 *
 * @param percentage The percentage to calculate. Must be of the same type as the current number.
 * @return The calculated percentage value of the current number, maintaining the original type.
 * @throws UnsupportedOperationException if the number type is not one of the supported types.
 */
@Suppress("UNCHECKED_CAST")
fun <T : Number> T.toPercentage(percentage: T): T {
    return when (this) {
        is Int -> (this * percentage.toInt() / 100) as T
        is Double -> (this * percentage.toDouble() / 100) as T
        is Float -> (this * percentage.toFloat() / 100) as T
        is Long -> (this * percentage.toLong() / 100) as T
        is Short -> (this * percentage.toShort() / 100) as T
        is Byte -> (this * percentage.toByte() / 100) as T
        else -> throw UnsupportedOperationException("Unsupported number type for percentage calculation.")
    }
}