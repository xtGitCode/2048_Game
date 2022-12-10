package com.example.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Account implements Comparable<Account> {
    private long highScore = 0;
    private String userName ;
    private String password ;
    private static ArrayList<Account> accounts = new ArrayList<>();

    public Account(String userName, String password, String highScore){
        this.userName=userName;
        this.password=password;
        this.highScore=Long.parseLong(highScore);
    }

    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), highScore);
    }

    public void addToScore(long score) {
        this.highScore += score;
    }

    protected long getScore() {
        return highScore;
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

    static Account makeNewAccount(String userName, String password, long highScore) throws IOException {
        highScore = Long.parseLong(Long.toString(highScore));
        StringBuilder sb = new StringBuilder();
        sb.append(userName + "," + highScore + "\n");
        sb.append(password + "\n");

        File file = new File("users.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(sb.toString());
        bw.close();

        Account account = new Account(userName,password,"0"); //add to arraylist
        accounts.add(account);
        return account;
    }

    public static void createdAccounts(String f) throws IOException {
        File file = new File(f);
        if (file.exists()) {
            Scanner s = new Scanner(new File(f));
            while (s.hasNextLine()) {
                String str = s.nextLine();
                String[] arrofStr = str.split(",");
                String userName = arrofStr[0];
                String highScore = arrofStr[1];
                String password = s.nextLine();
                Account account = new Account(userName,password,highScore); //add to arraylist
                accounts.add(account);
            }
            s.close();
        }

    }
}
