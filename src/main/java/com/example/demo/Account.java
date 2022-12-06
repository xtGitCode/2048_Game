package com.example.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Account implements Comparable<Account> {
    private long score = 0;
    private String userName ;
    private String password ;
    private static ArrayList<Account> accounts = new ArrayList<>();

    public Account(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    public void addToScore(long score) {
        this.score += score;
    }

    private long getScore() {
        return score;
    }

    private String getUserName() {
        return userName;
    }

    protected String getPassword() {
        return password;
    }

    static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;

    }

    static Account makeNewAccount(String userName, String password) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(userName + "\n");
        sb.append(password + "\n");

        File file = new File("users.txt");
        FileWriter W = new FileWriter(file,true);
        W.append(sb.toString());
        W.close();

        Account account = new Account(userName,password); //add to arraylist
        accounts.add(account);
        return account;
    }

    public static void createdAccounts(String f) throws IOException {
        File file = new File(f);
        if (file.exists()) {
            Scanner s = new Scanner(new File(f));
            while (s.hasNextLine()) {
                String userName = s.nextLine();
                String password = s.nextLine();
                makeNewAccount(userName, password);
            }
            s.close();
        }

    }
}
