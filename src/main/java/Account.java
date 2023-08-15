public class Account {
    private final int accountID;
    private final String ownerName;
    private long balance;
    private long receivable = 0;
    private long payable = 0;

    public Account(int accountID, String ownerName, long balance) {
        this.accountID = accountID;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getReceivable() {
        return receivable;
    }

    public void setReceivable(long receivable) {
        this.receivable = receivable;
    }

    public long getPayable() {
        return payable;
    }

    public void setPayable(long payable) {
        this.payable = payable;
    }
}
