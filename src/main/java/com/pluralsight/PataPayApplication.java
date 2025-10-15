package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PataPayApplication {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

//        add home screen
//        deposit,make payment(debit), ledger,exit
        double deposit = 0;
        boolean running = true;
        String choice;


        public static void runMainMenu(){
        while(running) {

            System.out.println("**********");
            System.out.println("HOME SCREEN");
            System.out.println("**********");
            System.out.println("D) Make Deposit");
            System.out.println("P) Make Payment(Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("**********");

            System.out.println("Please make your selection from the menu:");
            choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    makeDeposit(deposit);
                    break;
                case "P":
                    System.out.println("MAKE A PAYMENT");
                    break;
                case "L":
                    System.out.println("SHOW LEDGER");
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("INVALID CHOICE");

            }
        }}
        scanner.close();


    }



}
