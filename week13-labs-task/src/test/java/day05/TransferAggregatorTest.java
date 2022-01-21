package day05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransferAggregatorTest {

    @TempDir
    File temporaryFolder;

    @Test
    void readTransfersTest() {
        Path validInput = Paths.get("src/test/resources/transfers.csv");
        TransferAggregator transferAggregator = new TransferAggregator();
        List<TransferPerClient> expected = new ArrayList<>(Arrays.asList(new TransferPerClient("abcde"),
                new TransferPerClient("fghjk"),
                new TransferPerClient("lputr")));
        expected.get(0).increase(0);
        expected.get(0).increase(0);
        expected.get(0).increase(0);
        expected.get(0).increase(0);

        expected.get(1).decrease(500);
        expected.get(1).increase(0);
        expected.get(1).increase(0);

        expected.get(2).increase(500);
        expected.get(2).increase(0);
        expected.get(2).increase(0);

        assertEquals(expected.toString(), transferAggregator.readTransfers(validInput).toString());
    }

    @Test
    void readInvalidFileTransfersTest() {
        TransferAggregator transferAggregator = new TransferAggregator();
        Path invalidInput = Paths.get("src/test/resources/transfers_.csv");
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () ->
                transferAggregator.readTransfers(invalidInput));
        assertEquals("Cannot read file!", iae.getMessage());
        assertEquals(NoSuchFileException.class, iae.getCause().getClass());
    }

    @Test
    void readFileWithInvalidAmountTransfersTest() {
        Path invalidAmountInput = Paths.get("src/test/resources/transfers_with_invalid_amount.csv");
        TransferAggregator transferAggregator = new TransferAggregator();
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () ->
                transferAggregator.readTransfers(invalidAmountInput));
        assertEquals("Invalid data in the file!", iae.getMessage());
        assertEquals(NumberFormatException.class, iae.getCause().getClass());
    }

    @Test
    void readFileWithEmptyAmountTransfersTest() {
        TransferAggregator transferAggregator = new TransferAggregator();
        Path emptyAmountInput = Paths.get("src/test/resources/transfers_with_empty_amount.csv");
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () ->
                transferAggregator.readTransfers(emptyAmountInput));
        assertEquals("Invalid data in the file!", iae.getMessage());
        assertEquals(ArrayIndexOutOfBoundsException.class, iae.getCause().getClass());
    }

    @Test
    void writeTransfersTest() throws IOException {
        Path validInput = Paths.get("src/test/resources/transfers.csv");
        TransferAggregator transferAggregator = new TransferAggregator();
        Path validWritePath = temporaryFolder.toPath().resolve("transfers_sum.csv");
        List<String> expectedList = new ArrayList<>(Arrays.asList("abcde,0,4", "fghjk,-500,3", "lputr,500,3"));
        transferAggregator.writeTransfers(validInput, validWritePath);
        List<String> fileContent = Files.readAllLines(validWritePath);
        assertEquals(expectedList, fileContent);
    }
}