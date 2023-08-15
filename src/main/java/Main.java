import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


class Main {
    public static void main(String[] args) {
        FileReader transactionsReader;
        FileReader accountsReader;
        try {
            transactionsReader = new FileReader("Transactions.csv");
            accountsReader = new FileReader("Accounts.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Transaction> transactions = new ArrayList<>();
        HashMap<Integer,Account> accounts = new HashMap<>();
        CSVReader transactionCsv = new CSVReader(transactionsReader);
        CSVReader accountCsv = new CSVReader(accountsReader);
        String[] nextTransaction;
        String[] nextAccount;

        while (true) {
            try {
                if ((nextTransaction = transactionCsv.readNext()) == null || (nextAccount = accountCsv.readNext()) == null) break;
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }

            List<String> tempTransaction = Arrays.stream(nextTransaction).toList();
            List<String> tempAccount = Arrays.stream(nextAccount).toList();

            int accountID = Integer.parseInt(tempAccount.get(0).replace("\uFEFF", ""));
            String accountName = tempAccount.get(1).replace("\uFEFF", "");
            long balance = Long.parseLong(tempAccount.get(2).replace(",", ""));
            accounts.put(accountID,new Account(accountID,accountName,balance));

            int srcID = Integer.parseInt(tempTransaction.get(0).replace("\uFEFF", ""));
            int desID = Integer.parseInt(tempTransaction.get(1).replace("\uFEFF", ""));
            long amount = Long.parseLong(tempTransaction.get(2).replace(",", ""));
            transactions.add(new Transaction(accounts.get(srcID),accounts.get(desID),amount));
        }



    }
}


