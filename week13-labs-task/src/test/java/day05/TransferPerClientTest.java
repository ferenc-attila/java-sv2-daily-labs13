package day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferPerClientTest {

    @Test
    void createTest() {
        TransferPerClient transferPerClient = new TransferPerClient("abcde");
        assertEquals("abcde", transferPerClient.getClientId());
    }

    @Test
    void increaseTest() {
        TransferPerClient transferPerClient = new TransferPerClient("z012");
        transferPerClient.increase(1000);
        assertEquals(1000, transferPerClient.getSum());
        assertEquals(1, transferPerClient.getNumberOfTransactions());
        transferPerClient.increase(500);
        assertEquals(1500, transferPerClient.getSum());
        assertEquals(2, transferPerClient.getNumberOfTransactions());
    }

    @Test
    void decreaseTest() {
        TransferPerClient transferPerClient = new TransferPerClient("z012y");
        transferPerClient.decrease(15000);
        assertEquals(-15000, transferPerClient.getSum());
        assertEquals(1, transferPerClient.getNumberOfTransactions());
        transferPerClient.decrease(250);
        assertEquals(-15250, transferPerClient.getSum());
        assertEquals(2, transferPerClient.getNumberOfTransactions());
    }

    @Test
    void toCsvStringTest() {
        TransferPerClient transferPerClient = new TransferPerClient("zHgt453Sd");
        transferPerClient.increase(200);
        transferPerClient.decrease(80);
        assertEquals("zHgt453Sd,120,2", transferPerClient.toString());
    }
}