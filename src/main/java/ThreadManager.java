import java.util.List;

public class ThreadManager {
    private final List<Transaction> transactionList;
    private final List<Account> accountList;

    public ThreadManager(List<Transaction> transactionList, List<Account> accountList) {
        this.transactionList = transactionList;
        this.accountList = accountList;
    }

}
