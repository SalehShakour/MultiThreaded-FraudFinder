public record Transaction(Account source, Account destination, long amount) {
    public void apply(){
        synchronized (source){
            source.setReceivable(source.getReceivable() + amount);
        }
        synchronized (destination){
            destination.setPayable(destination.getPayable() + amount);
        }

    }
}
