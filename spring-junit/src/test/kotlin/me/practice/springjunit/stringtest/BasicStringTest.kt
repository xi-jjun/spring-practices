package me.practice.springjunit.stringtest

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BasicStringTest {
    @DisplayName("a와 b를 더하면, 새로운 ab 문자열 객체가 반환된다.")
    @Test
    fun plusMethodTest() {
        val str1 = "a"
        val str2 = "b"
        assertEquals("ab", str1.plus(str2))
        assertEquals("a", str1) // 원본 문자열 유지
    }

    @DisplayName("a와 b를 더하면, ab 문자열 객체가 반환되고 기존 문자열은 수정된다.")
    @Test
    fun plusMethodFailTest() {
        val str1 = "a"
        val str2 = "b"
        assertEquals("ab", str1.plus(str2))
        assertEquals("ab", str1) // 원본 문자열 유지안된다고 가정
    }
}
