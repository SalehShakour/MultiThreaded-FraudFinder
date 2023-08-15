import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private final List<Transaction> transactionList;
    private final HashMap<Integer, Account> accountList;

    public ThreadManager(List<Transaction> transactionList, HashMap<Integer, Account> accountList) {
        this.transactionList = transactionList;
        this.accountList = accountList;
        start();
    }
    public void start(){
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Runnable> tasks = transactionList.stream()
                .map(transaction -> (Runnable) transaction::apply)
                .toList();
        for (Runnable task : tasks) {
            executor.execute(task);
        }
        executor.shutdown();


        for (Account account:accountList.values()){
            System.out.println("ID: " + account.getAccountID());
            System.out.println("balance before changes: " + account.getBalance());
            System.out.println("payable : " + account.getPayable());
            System.out.println("receivable : " + account.getReceivable());
            long newBalance = account.getBalance() + account.getPayable() - account.getReceivable();
            System.out.println("new balance : " + newBalance + "\n" + " -------------- ");
        }

    }


//    public int getOptimalThreadNumber(){
//        int availableCores = Runtime.getRuntime().availableProcessors();
//        double waitTime = ...; // time a task spends waiting in the queue
//        double serviceTime = ...; // time it takes for a task to be executed
//        int optimalThreads = (int) (availableCores * (1 + waitTime / serviceTime));
//
//    }

}
