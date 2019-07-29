package com.whaletail.capecanaveral

import org.junit.Test
import java.util.*

class DataSetTest {


    @Test
    fun multipleMissingNumbers(){
        val arr = intArrayOf(1, 1, 1, 2, 3, 6, 6, 7, 7, 8)

        val register = IntArray(arr.size)

        arr.forEachIndexed { index, i ->
            register[i] = 1
        }

        register.forEachIndexed { index, i -> if (i == 0) print("$index ") }

    }




    class QuickSort(val arr: IntArray) {

        fun sort(start: Int, end: Int) {
            println("$start $end")
            var i = start
            var j = end

            val pivot = arr[start + (end - start) / 2]

            while (i <= j) {
                while (arr[i] < pivot) {
                    i++
                }
                while (arr[j] > pivot) {
                    j--
                }

                if (i <= j) {
                    swap(i, j)
                    i++
                    j--
                }

            }

            if (start < j) {
                sort(start, j)
            }

            if (i < end) {
                sort(i, end)
            }

        }


        private fun swap(i: Int, j: Int) {
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }

    }

    @Test
    fun quickSortTest() {


        val unsortedArr = intArrayOf(1, 3, 5, 2, 4, 6, 8, 7)
        val shouldLookLikeArr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        QuickSort(unsortedArr).sort(0, unsortedArr.lastIndex)
        assert(unsortedArr.contentEquals(shouldLookLikeArr))
    }

    @Test
    fun firstNonRepeatingCharacterTest() {
        assert(getFirstNonRepeatingCharacter("asd") == 'a')
        assert(getFirstNonRepeatingCharacter("aasd") == 's')
        assert(getFirstNonRepeatingCharacter("aassdd") == null)
    }


    fun getFirstNonRepeatingCharacter(s: String): Char? {
        val map = linkedMapOf<Char, Int>()

        s.forEach {
            if (map.containsKey(it)) {
                map[it] = map.getValue(it) + 1
            } else {
                map[it] = 1
            }
        }

        return map.entries.firstOrNull { it.value == 1 }?.key

    }

    @Test
    fun missingNumber() {
        assert(getMissingNumberSimple(listOf(1, 2, 3, 4, 5, 7, 8), 8F) == 6)


        assert(getMissingNumber(listOf(1, 2, 5, 6, 9), 9).size == 4)
    }

    fun getMissingNumber(list: List<Int>, totalCount: Int): List<Int> {
        val missingNumbers = mutableListOf<Int>()
        val missingCount = totalCount - list.size

        val bitSet = BitSet.valueOf(list.map { it.toLong() }.toLongArray())
        list.forEach { bitSet.set(it - 1) }

        var lastMissingIndex = 0
        (0 until missingCount).forEach {
            print("$lastMissingIndex ")
            lastMissingIndex = bitSet.nextClearBit(lastMissingIndex)
            missingNumbers.add(++lastMissingIndex)
            println(lastMissingIndex)
        }

        return missingNumbers
    }

    fun getMissingNumberSimple(list: List<Int>, totalCount: Float): Int {
        val expectedSum = totalCount * ((totalCount + 1) / 2)
        return expectedSum.toInt() - list.sum()
    }


    @Test
    fun removeDuplicatesTest() {
        assert(
            removeDuplicates(
                intArrayOf(
                    1,
                    2,
                    3,
                    3,
                    3,
                    3,
                    4,
                    5,
                    5,
                    5
                )
            ).also { println(it.joinToString(",")) }.size == 5
        )
    }

    fun removeDuplicates(arrayWithDuplicates: IntArray): IntArray {
        arrayWithDuplicates.sort()
        var duplicatesSize = 0
        var prev = arrayWithDuplicates[0]
        (1 until arrayWithDuplicates.size).forEach {
            val current = arrayWithDuplicates[it]
            if (prev == current) {
                duplicatesSize++
            }
            prev = current
        }
        val arrayWithoutDuplicates = IntArray(arrayWithDuplicates.size - duplicatesSize)

        println(arrayWithDuplicates.joinToString(","))
        println(arrayWithoutDuplicates.size)
        println(duplicatesSize)

        prev = arrayWithDuplicates[0]
        arrayWithoutDuplicates[0] = prev
        var index = 1
        (1 until arrayWithDuplicates.size).forEach {
            val current = arrayWithDuplicates[it]
            if (prev != current) {
                arrayWithoutDuplicates[index] = current
                println("$index| $current| ${arrayWithoutDuplicates[index]}")
                index++
            }
            prev = current
        }
        return arrayWithoutDuplicates
    }

    @Test
    fun sumOfPairOfIntegers() {
        get(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 6)
    }

    fun get(array: IntArray, n: Int) {
        val set = mutableSetOf<Int>()

        array.forEach {
            val target = n - it

            if (!set.contains(target)) {
                set.add(it)
            } else {
                println("$it + $target")
            }

        }

    }


}