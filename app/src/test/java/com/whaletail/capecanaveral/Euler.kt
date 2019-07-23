package com.whaletail.capecanaveral

import org.junit.Test
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt

const val FIBONACCI_LIMIT = 4_000_000
val FIBONACCI_FORMAT = DecimalFormat("#,###")
const val LARGEST_PRIME_NUMBER_VALUE = 600851475143
const val LARGEST_PRIME_NUMBER_TEST_VALUE = 13195

const val PALINDROME_MAX = 999 * 999
const val PALINDROME_MIN = 100 * 100

const val LIFE_PER_MONTH = 450
const val UAH_USD = 26
const val PERC_TO_CRYPTO = 0.1
const val PERC_TO_BANK = 0.5
const val PER_MONTH1 = 2000
const val PER_MONTH2 = 2500
const val PER_MONTH3 = 3000
const val PER_MONTH4 = 4000
const val PERC_DEPOSIT = 0.14

private fun getRawIncome(salary: Int) =
    ((salary - LIFE_PER_MONTH - salary * PERC_TO_CRYPTO) * PERC_TO_BANK).toInt() * UAH_USD

class Euler {


    @Test
    fun calculateDeposit() {

        var initInvestment = 0

        var income = 0

        var totalCrypto = 0

        for (i in 1..6) {
            totalCrypto += (PER_MONTH1 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH1)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        println("--------RAISE-------")
        println("--------21-------")

        for (i in 7..12) {
            totalCrypto += (PER_MONTH2 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH2)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        println("--------RAISE-------")
        for (i in 13..18) {
            totalCrypto += (PER_MONTH3 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH3)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        println("--------22-------")
        for (i in 19..24) {
            totalCrypto += (PER_MONTH3 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH3)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        println("--------RAISE-------")
        for (i in 25..30) {
            totalCrypto += (PER_MONTH4 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH4)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        println("--------23-------")
        for (i in 31..36) {
            totalCrypto += (PER_MONTH4 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH4)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        for (i in 37..42) {
            totalCrypto += (PER_MONTH4 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH4)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        println("--------24-------")
        for (i in 43..48) {
            totalCrypto += (PER_MONTH4 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH4)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        for (i in 49..54) {
            totalCrypto += (PER_MONTH4 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH4)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        println("--------25-------")
        for (i in 55..60) {
            totalCrypto += (PER_MONTH4 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH4)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        for (i in 61..66) {
            totalCrypto += (PER_MONTH4 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH4)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        println("--------26-------")
        for (i in 67..72) {
            totalCrypto += (PER_MONTH4 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH4)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        for (i in 73..78) {
            totalCrypto += (PER_MONTH4 * PERC_TO_CRYPTO).toInt()
            initInvestment += getRawIncome(PER_MONTH4)
            val perMonth = ((initInvestment + income) * PERC_DEPOSIT * 0.8 / 12).toInt()
            income += perMonth
            println("month $i: investment: $initInvestment; income: $income; p/m: ${perMonth}")
        }
        println("--------27-------")

        totalCrypto *= UAH_USD

        println("init investment: $initInvestment")
        println("income: $income")
        println("crypto: ${totalCrypto}")
        println("total: ${income + initInvestment + totalCrypto}")
    }

    @Test
    fun largestPalindromeProduct() {
        for (palindrome in PALINDROME_MAX downTo PALINDROME_MIN) {
            if (palindrome.isPalindrome()) {
                for (i in 999 downTo 100) {
                    if ((palindrome % i == 0).also { print("$palindrome % $i == 0: $it; ") } &&
                        (palindrome / i >= 100).also { print(" $palindrome / $i >= 100: $it; ") } &&
                        (palindrome / i <= 999).also { println(" $palindrome / $i <= 999: $it") }) {
                        println(palindrome)
                        println("$i * ${palindrome / i}")
                        return
                    }
                    if (palindrome / i < 100 || palindrome / i > 999) {
                        break
                    }
                }
            }
        }
    }

    private fun Int.isPalindrome(): Boolean {
        if (this % 10 == 0) return false
        var cache = this
        val left: Int
        var right = 0
        for (i in 2 downTo 0) {
            right += (cache % 10) * 10.toDouble().pow(i).toInt()
            cache /= 10
        }
        left = cache
        return left == right
    }

    @Test
    fun multiplesOf3And5() {
        (3 until 1000)
            .filterTo(mutableSetOf()) { it % 3 == 0 || it % 5 == 0 }
            .sum()
            .also { print(" = $it") }
    }

    @Test
    fun evenFibonacciNumbers() {

        generateSequence(0 to 1) { it.second to it.first + it.second }
            .takeWhile { it.first + it.second < FIBONACCI_LIMIT }
            .map { it.second }
            .filter { it % 2 == 0 }
            .also {
                it.joinToString(" + ")
                    .also { print(it) }
            }
            .sum()
            .also { print(" = ${FIBONACCI_FORMAT.format(it)}") }

    }

    @Test
    fun largestPrimeFactor() {

        val primeFactors = mutableListOf<Long>()

        var n = LARGEST_PRIME_NUMBER_VALUE

        while (n % 2 == 0L) {
            n /= 2
        }

        for (i in 3..sqrt(n.toDouble()).toLong() step 2) {
            while (n % i == 0L) {
                primeFactors.add(i)
                n /= i
            }
        }

        primeFactors.max()
            .also { print(it) }

    }


}