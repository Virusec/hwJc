package multithreading.bankAccount;

/**
 * @author Anatoliy Shikin
 */
public class BankAccount {
    private final int accountNumber;
    private int balance;

    public BankAccount(int accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public synchronized void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public synchronized boolean withdraw(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized int getBalance() {
        return balance;
    }
}
