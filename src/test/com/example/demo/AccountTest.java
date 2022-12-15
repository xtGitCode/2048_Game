package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class AccountTest {

    @Test
    void accountHaveBeenExist() {
        Account oldAcc = new Account("test", "123", "50");
        Account.getAccounts().add(oldAcc);
        Account actual = Account.accountHaveBeenExist("test");

        Assertions.assertEquals(oldAcc,actual);
    }

    @Test
    void makeNewAccount() {
        Account newAcc = new Account("test2", "123", "0");
        Account.getAccounts().add(newAcc);

        Assertions.assertEquals(newAcc, Account.getAccounts().get(0));
    }

    @Test
    void sortAccounts() {
        Account test1 = new Account("test1","123","1000");
        Account test2 = new Account("test2","123","500");
        Account test3 = new Account("test3","123","100");

        ArrayList<Account> unsorted = new ArrayList<>();
        unsorted.add(test3);
        unsorted.add(test1);
        unsorted.add(test2);

        ArrayList<Account> sorted = new ArrayList<>();
        sorted.add(test1);
        sorted.add(test2);
        sorted.add(test3);

        Account.sortAccounts(unsorted);

        Assertions.assertEquals(sorted, unsorted);
    }
}