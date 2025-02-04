import java.util.ArrayList;
import java.util.List;

public class BankingSystem {
    private List<Account> accounts;

    public BankingSystem() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void processTransaction(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.updateBalance(amount);
            FraudPrediction fraudPrediction = new FraudPrediction();
            if (fraudPrediction.isFraudulentTransaction(accountNumber, amount)) {
                System.out.println("Alert: Fraudulent transaction detected!");
            } else {
                System.out.println("Transaction processed successfully.");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        bank.addAccount(new Account("12345", 5000.0));
        bank.processTransaction("12345", -1000.0);
    }
}

class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }
}

