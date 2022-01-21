package day05;

import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        TransferAggregator transferAggregator = new TransferAggregator();
        List<TransferPerClient> transfers = transferAggregator.readTransfers(Path.of("src/main/resources/transfers.csv"));
        transferAggregator.writeTransfers(Path.of("src/main/resources/transfers_sum.csv"));

        for (TransferPerClient actual : transfers) {
            System.out.printf("%s %,12d %5d%n", actual.getClientId(), actual.getSum(), actual.getNumberOfTransactions());
        }
    }
}
