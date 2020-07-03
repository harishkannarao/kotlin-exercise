package com.harishkannarao.kotlin.exercise.sample

class CharacterCalculator {
    fun calculateMaxOccurrence(input: String): Map<Char, Int> {
        return calculateMaxOccurrenceInternal(input.toList(), emptyList(), emptyMap())
    }

    private tailrec fun calculateMaxOccurrenceInternal(chars: List<Char>, occurrenceContext: List<Pair<Char, Int>>, acc: Map<Char, Int>): Map<Char, Int> {
        if (chars.isEmpty()) {
            return acc
        }
        val char = chars.first()
        val previousCount = occurrenceContext.lastOrNull() ?: Pair(char, 0)
        val remainder = chars.takeLast(chars.size - 1)
        return when (char) {
            ' ' -> {
                calculateMaxOccurrenceInternal(remainder, occurrenceContext, acc)
            }
            previousCount.first -> {
                val currentOccurrence = Pair(char, previousCount.second + 1)
                val updatedContext = occurrenceContext.plus(currentOccurrence)
                val currentCount = acc[char] ?: 0
                val updatedAcc = if (currentOccurrence.second > currentCount) acc.plus(currentOccurrence) else acc
                calculateMaxOccurrenceInternal(remainder, updatedContext, updatedAcc)
            }
            else -> {
                val currentOccurrence = Pair(char, 1)
                val updatedContext = occurrenceContext.plus(currentOccurrence)
                val currentCount = acc[char] ?: 0
                val updatedAcc = if (currentOccurrence.second > currentCount) acc.plus(currentOccurrence) else acc
                calculateMaxOccurrenceInternal(remainder, updatedContext, updatedAcc)
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
