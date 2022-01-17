package NullObject;

interface Log{
    void info(String message);
    void warn(String message);
}

class ConsoleLog implements Log{

    @Override
    public void info(String message) {
        System.out.println("[INFO]: " + message);
    }

    @Override
    public void warn(String message) {
        System.out.println("[WARNING]: " + message);
    }
}

final class NullLog implements Log{

    @Override
    public void info(String message) {

    }

    @Override
    public void warn(String message) {

    }
}

class BankAccount{
    private Log log;
    public int balance;

    public BankAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount) {
        balance += amount;
        log.info("Deposited: " + amount);
    }
}

public class NullObject {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(new ConsoleLog());
        bankAccount.deposit(100);

        // if you wanted not to log transactions of bankAccount object, you can declare null instead of new ConsoleLog()
        // BankAccount bankAccount = new BankAccount(null);
        // the deposit method uses log.info(), So passing null in BankAccount constructor will give an error

        BankAccount bankAccount1 = new BankAccount(new NullLog());
    }
}
