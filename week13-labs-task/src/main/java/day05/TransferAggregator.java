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
        try (BufferedReader br = Files.newBufferedReader(path)) {
            while ((line = br.readLine()) != null) {
                parseRow(line, transfers);
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("Invalid data in the file!", exception);
        }
        return transfers;
    }

    private void parseRow(String line, Map<String, TransferPerClient> transfers) {
        String[] row = line.split(",");
        String sourceClientId = row[0];
        String targetClientId = row[1];
        int amount = Integer.parseInt(row[2]);
        TransferPerClient source = transfers.computeIfAbsent(sourceClientId, k -> new TransferPerClient(sourceClientId));
        source.decrease(amount);
        TransferPerClient target = transfers.computeIfAbsent(targetClientId, k -> new TransferPerClient(targetClientId));
        target.increase(amount);
    }

    public void writeTransfers(Path input, Path output) {
        List<TransferPerClient> transfers = readTransfers(input);
        List<String> contentToWrite = createContentOfFile(transfers);
        try {
            Files.write(output, contentToWrite);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot write file!", ioe);
        }
    }

    private List<String> createContentOfFile(List<TransferPerClient> transfers) {
        List<String> contentToWrite = new ArrayList<>();
        for (TransferPerClient actual : transfers) {
            contentToWrite.add(actual.toString());
        }
        return contentToWrite;
    }
}
