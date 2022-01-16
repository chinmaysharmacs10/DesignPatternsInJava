package Memento;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

class BankAccount{
    public int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public Memento deposit(int amount) {
        balance += amount;
        return new Memento(balance);
    }

    public void restore(Memento memento) {
        balance = memento.getBalance();
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

@Getter
public class Memento {
    private int balance;

    public Memento(int balance) {
        this.balance = balance;
    }
}

class demo{
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100);
        Memento memento1 = bankAccount.deposit(50);
        Memento memento2 = bankAccount.deposit(25);
        System.out.println(bankAccount);

        // restore to memento1
        bankAccount.restore(memento1);
        System.out.println(bankAccount);

        // restore to memento2
        bankAccount.restore(memento2);
        System.out.println(bankAccount);

    }
}
