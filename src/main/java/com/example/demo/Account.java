/**
 * @author Xiao Thung Gan - modified
 */

package com.example.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates and manages accounts when user log in or sign up
 */
public class Account implements Comparable<Account> {
    private long highScore = 0;
    private String userName ;
    private String password ;
    private static ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Account (the constructor)
     * @param userName User's name
     * @param password User's password for authentication
     * @param highScore User's best score in game
     */
    public Account(String userName, String password, String highScore){
        this.userName=userName;
        this.password=password;
        this.highScore=Long.parseLong(highScore);
    }

    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), highScore);
    }

    public long getScore() {
        return highScore;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * Check whether user's account exist in system database
     * @param userName User input username
     * @return user's account if it exists, else, return null.
     */
    public static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;
    }

    /**
     * Create new user account and store in database
     * @param userName User's username
     * @param password User's password for authentication
     * @param highScore User's best score in game
     * @throws IOException
     * Return: void
     */
    public static void makeNewAccount(String userName, String password, long highScore) throws IOException {
        highScore = Long.parseLong(Long.toString(highScore));
        StringBuilder sb = new StringBuilder();
        sb.append(userName + "," + highScore + "\n");
        sb.append(password + "\n");

        File file = new File("src/users.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(sb.toString());
        bw.close();

        Account account = new Account(userName,password,"0"); //add to arraylist
        accounts.add(account);
    }

    /**
     * Initialize all created accounts in database to arraylist
     * @param pathName path to text file (database storing accounts)
     * @throws IOException
     * Return: void
     */
    public static void createdAccounts(String pathName) throws IOException {
        accounts.clear();
        File file = new File(pathName);
        if (file.exists()) {
            Scanner s = new Scanner(new File(pathName));
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
            sortAccounts(accounts);
        }

    }

    /**
     * Sort accounts in arraylist in ascending order of high score
     * @param accounts Arraylist containing all created accounts
     * Return: void
     */
    public static void sortAccounts(ArrayList<Account> accounts){
        boolean sorted = false;
        Account temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < accounts.size() - 1; i++) {
                if (accounts.get(i).getScore() < accounts.get(i+1).getScore()) {
                    temp = accounts.get(i);
                    accounts.set(i, accounts.get(i + 1));
                    accounts.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }
}
