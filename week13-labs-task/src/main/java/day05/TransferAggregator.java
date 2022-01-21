package day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TransferAggregator {

    public List<TransferPerClient> readTransfers(Path path) {
        Map<String, TransferPerClient> transfers = parseTransaction(path);
        return new ArrayList<>(transfers.values());
    }

    private Map<String, TransferPerClient> parseTransaction(Path path) {
        String line;
        Map<String, TransferPerClient> transfers = new TreeMap<>();
        try (BufferedReader br = Files.newBufferedReader(path)){
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (transfers.containsKey(row[0])) {
                    transfers.get(row[0]).increase(Integer.parseInt(row[2]));
                } else {
                    transfers.put(row[0], new TransferPerClient(row[0], Integer.parseInt(row[2]), 1));
                }
                if (transfers.containsKey(row[1])) {
                    transfers.get(row[1]).decrease(Integer.parseInt(row[2]));
                } else {
                    transfers.put(row[1], new TransferPerClient(row[1], Integer.parseInt(row[2]), 1));
                }
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        } catch (NumberFormatException | NullPointerException exception) {
            throw new IllegalArgumentException("Invalid data in the file!", exception);
        }
        return transfers;
    }
}
