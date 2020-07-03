package com.harishkannarao.kotlin.exercise.sample

class MaxLetterSequence {
    fun calculate(input: String): Map<Char, Int> {
        return calculateInternal(input.toList(), emptyList(), emptyMap())
    }

    private tailrec fun calculateInternal(chars: List<Char>, occurrenceContext: List<Pair<Char, Int>>, acc: Map<Char, Int>): Map<Char, Int> {
        if (chars.isEmpty()) {
            return acc
        }
        val char = chars.first()
        val previousCount = occurrenceContext.lastOrNull() ?: Pair(char, 0)
        val remainder = chars.takeLast(chars.size - 1)
        return when (char) {
            ' ' -> {
                calculateInternal(remainder, occurrenceContext, acc)
            }
            previousCount.first -> {
                val currentOccurrence = Pair(char, previousCount.second + 1)
                val updatedContext = occurrenceContext.plus(currentOccurrence)
                val currentCount = acc[char] ?: 0
                val updatedAcc = if (currentOccurrence.second > currentCount) acc.plus(currentOccurrence) else acc
                calculateInternal(remainder, updatedContext, updatedAcc)
            }
            else -> {
                val currentOccurrence = Pair(char, 1)
                val updatedContext = occurrenceContext.plus(currentOccurrence)
                val currentCount = acc[char] ?: 0
                val updatedAcc = if (currentOccurrence.second > currentCount) acc.plus(currentOccurrence) else acc
                calculateInternal(remainder, updatedContext, updatedAcc)
            }
        }
    }
}
