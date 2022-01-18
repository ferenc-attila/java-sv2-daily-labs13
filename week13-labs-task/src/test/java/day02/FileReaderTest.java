package day02;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void readFileTest() {
        FileReader fileReader = new FileReader();
        Path path = Path.of("src/test/resources/result.txt");
        fileReader.getAnswers(path);
        assertEquals(4, fileReader.getAnswers().size());
        assertTrue(fileReader.getAnswers().containsKey("AH2"));
        assertEquals("ACCBX", fileReader.getAnswers().get("AB123"));
    }

    @Test
    void readInvalidFileTest() {
        FileReader fileReader = new FileReader();
        Path path = Path.of("src/test/resources/result_.txt");
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> fileReader.getAnswers(path));
        assertEquals("Can't read file!", iae.getMessage());
        assertEquals(NoSuchFileException.class, iae.getCause().getClass());
    }

    @Test
    void isRightAnswerTest() {
        FileReader fileReader = new FileReader();
        Path path = Path.of("src/test/resources/result.txt");
        fileReader.getAnswers(path);
        assertTrue(fileReader.isRightAnswer("AH2", 2));
        assertFalse(fileReader.isRightAnswer("AB123", 2));
    }

    @Test
    void isRightAnswerEmptyCompetitor() {
        FileReader fileReader = new FileReader();
        Path path = Path.of("src/test/resources/result.txt");
        fileReader.getAnswers(path);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> fileReader.isRightAnswer("", 1));
        assertEquals("Competitor can't be empty!", iae.getMessage());
    }

    @Test
    void isRightAnswerInvalidCompetitor() {
        FileReader fileReader = new FileReader();
        Path path = Path.of("src/test/resources/result.txt");
        fileReader.getAnswers(path);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> fileReader.isRightAnswer("ABC123", 1));
        assertEquals("No such competitor: ABC123!", iae.getMessage());
    }

    @Test
    void isRightAnswerTooLargeNumberOfQuestion() {
        FileReader fileReader = new FileReader();
        Path path = Path.of("src/test/resources/result.txt");
        fileReader.getAnswers(path);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> fileReader.isRightAnswer("AB123", 6));
        assertEquals("6 is invalid value as number of question!", iae.getMessage());
    }

    @Test
    void isRightAnswerTooSmallNumberOfQuestion() {
        FileReader fileReader = new FileReader();
        Path path = Path.of("src/test/resources/result.txt");
        fileReader.getAnswers(path);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> fileReader.isRightAnswer("AB123", 0));
        assertEquals("0 is invalid value as number of question!", iae.getMessage());
    }

    @Test
    void getWinnerTest() {
        FileReader fileReader = new FileReader();
        Path path = Path.of("src/test/resources/result.txt");
        fileReader.getAnswers(path);
        assertEquals("GH1234", fileReader.getWinner());
    }
}