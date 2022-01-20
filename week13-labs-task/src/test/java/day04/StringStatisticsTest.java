package day04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StringStatisticsTest {

    @Test
    void getNumberOfVowelsTest() {
        StringStatistics statistics = new StringStatistics();
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('a',1);
        expected.put('e',3);
        expected.put('i',3);
        assertEquals(expected, statistics.getNumberOfVowels("This is a text in screen!"));
    }
}