package com.harishkannarao.kotlin.exercise.sample

class CharacterCalculator {
    fun calculateMaxOccurrence(input: String): Map<Char, Int> {
        val chars = input.asSequence()
        return calculateMaxOccurrenceInternal(chars, chars.first(), 0, emptyMap())
    }

    private tailrec fun calculateMaxOccurrenceInternal(chars: Sequence<Char>, previousChar: Char, previousCharCount: Int, acc: Map<Char, Int>): Map<Char, Int> {
        if (chars.none()) {
            return acc
        }
        val char = chars.first()
        val remainder = chars.drop(1)
        return when (char) {
            ' ' -> {
                calculateMaxOccurrenceInternal(remainder, char, 0, acc)
            }
            previousChar -> {
                val currentOccurrence = Pair(char, previousCharCount + 1)
                val currentCount = acc[char] ?: 0
                val updatedAcc = if (currentOccurrence.second > currentCount) acc.plus(currentOccurrence) else acc
                calculateMaxOccurrenceInternal(remainder, char, previousCharCount + 1, updatedAcc)
            }
            else -> {
                val currentOccurrence = Pair(char, 1)
                val currentCount = acc[char] ?: 0
                val updatedAcc = if (currentOccurrence.second > currentCount) acc.plus(currentOccurrence) else acc
                calculateMaxOccurrenceInternal(remainder, char, 1, updatedAcc)
            }
        }
    }

    fun calculateTotalOccurrence(input: String): Map<Char, Int> {
        return input.toList()
                .groupBy { it }
                .filter { entry -> entry.key != ' ' }
                .mapValues { entry -> entry.value.size }
    }
}
