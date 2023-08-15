import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


class Main {
    public static void main(String[] args) {
        FileReader filereader;
        try {
            filereader = new FileReader("Transactions.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Transaction> transactions = new ArrayList<>();
        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;

        // we are going to read data line by line
        while (true) {
            try {
                if ((nextRecord = csvReader.readNext()) == null) break;
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }

            List<String> temp = Arrays.stream(nextRecord).toList();

            int srcID = Integer.parseInt(temp.get(0).replace("\uFEFF", ""));
            int desID = Integer.parseInt(temp.get(1).replace("\uFEFF", ""));
            long amount = Long.parseLong(temp.get(2).replace(",", ""));
            transactions.add(new Transaction(srcID,desID,amount));
        }



    }
}


