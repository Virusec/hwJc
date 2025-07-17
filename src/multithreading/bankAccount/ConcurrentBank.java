package multithreading.bankAccount;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anatoliy Shikin
 */
public class ConcurrentBank {
    private final Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicInteger nextAccountNumber = new AtomicInteger(1);

    public BankAccount createAccount(int initialBalance) {
        int accNum = nextAccountNumber.getAndIncrement();
        BankAccount account = new BankAccount(accNum, initialBalance);
        accounts.put(accNum, account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, int amount) {
        BankAccount firstLock = from.getAccountNumber() < to.getAccountNumber() ? from : to;
        BankAccount secondLock = from.getAccountNumber() < to.getAccountNumber() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    System.out.println("Transferred " + amount +
                            " from account " + from.getAccountNumber() +
                            " to account " + to.getAccountNumber());
                } else {
                    System.out.println("Transfer failed due to insufficient funds in account " + from.getAccountNumber());
                }
            }
        }
    }

    public int getTotalBalance() {
        int total = 0;
        for (BankAccount account : accounts.values()) {
            synchronized (account) {
                total += account.getBalance();
            }
        }
        return total;
    }
}
