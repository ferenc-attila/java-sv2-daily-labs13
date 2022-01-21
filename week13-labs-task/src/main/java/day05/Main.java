package day05;

import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        TransferAggregator transferAggregator = new TransferAggregator();
        List<TransferPerClient> transfers = transferAggregator.readTransfers(Path.of("src/main/resources/transfers.csv"));

        for (TransferPerClient actual : transfers) {
            System.out.println(actual);
        }
    }
}
