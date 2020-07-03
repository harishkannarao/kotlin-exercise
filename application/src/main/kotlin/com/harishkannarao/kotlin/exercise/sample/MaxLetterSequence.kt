package com.harishkannarao.kotlin.exercise.sample

class MaxLetterSequence {
    fun calculate(input: String): Map<Char, Int> {
        val chars = input.toList()
        val charWithOccurrence = calculateInternal(chars, emptyList())
        return calculateMaxOccurrence(charWithOccurrence, emptyMap())
    }

    private tailrec fun calculateMaxOccurrence(chars: List<Pair<Char, Int>>, acc: Map<Char, Int>): Map<Char, Int> {
        if (chars.isEmpty()) {
            return acc
        }
        val char = chars.first()
        val remainder = chars.takeLast(chars.size - 1)
        val currentCount = acc[char.first] ?: 0
        return if (char.second > currentCount) {
            calculateMaxOccurrence(remainder, acc.plus(char))
        } else {
            calculateMaxOccurrence(remainder, acc)
        }
    }

    private tailrec fun calculateInternal(chars: List<Char>, occurrenceContext: List<Pair<Char, Int>>): List<Pair<Char, Int>> {
        if (chars.isEmpty()) {
            return occurrenceContext
        }
        val char = chars.first()
        val previousCount = occurrenceContext.lastOrNull() ?: Pair(char, 0)
        val remainder = chars.takeLast(chars.size - 1)
        return when (char) {
            ' ' -> {
                calculateInternal(remainder, occurrenceContext)
            }
            previousCount.first -> {
                calculateInternal(remainder, occurrenceContext.plus(Pair(char, previousCount.second + 1)))
            }
            else -> {
                calculateInternal(remainder, occurrenceContext.plus(Pair(char, 1)))
            }
        }
    }


}
