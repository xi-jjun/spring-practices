package me.practice.springjunit

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

/**
 * ref : https://kotlinlang.org/docs/jvm-test-using-junit.html#run-a-test
 * Junit 테스트 결과 파일이 어떤 형식으로 나오는지 확인하기 위한 간단 테스트 코드
 */
class JunitBasicTest {
    @DisplayName("1과 2를 더하면, 3이 된다.")
    @Test
    fun plusTest() {
        val a = 1
        val b = 2
        assertEquals(3, a + b)
    }

    @DisplayName("1에서 2를 빼면, -1이 된다.")
    @Test
    fun minusTest() {
        val a = 3
        val b = 2
        assertEquals(-1, a - b)
    }

    @DisplayName("Describe: #divide")
    @Nested
    inner class Describe_divide {
        @Nested
        @DisplayName("Context: 나눗셈을 할 때 0으로 나누게 될 경우")
        inner class Context_when_divide_by_zero {
            @Test
            @DisplayName("It: 에러가 발생한다.")
            fun error() {
                val a = 3
                val b = 0
                assertThrows<ArithmeticException> {
                    a / b
                }
            }
        }
    }
}
