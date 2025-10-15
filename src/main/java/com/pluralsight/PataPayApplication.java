package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Main class for the PataPay application
public class PataPayApplication {

    // Scanner to read user input
    static Scanner scanner = new Scanner(System.in);

    // CSV file name to store transactions
    private static final String FILE_NAME = "transactions.csv";

    // Main method - program starts here
    public static void main(String[] args) {
        runMainMenu(); // Start the home screen menu loop
    }

    // ------------------ HOME SCREEN ------------------
    public static void runMainMenu() {
        boolean running = true; // Flag to keep the program running

        while (running) {
            // Display home screen menu
            System.out.println("**********");
            System.out.println("HOME SCREEN");
            System.out.println("**********");
            System.out.println("D) Make Deposit");
            System.out.println("P) Make Payment(Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("**********");

            System.out.print("Please make your selection from the menu: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            // Perform action based on user choice
            switch (choice) {
                case "D":
                    makeDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    runLedger();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    running = false; // Exit the program
                    break;
                default:
                    System.out.println("INVALID CHOICE. Please enter D, P, L, or X.");
            }
        }
    }

    // ------------------ DEPOSITS ------------------
    public static void makeDeposit() {
        try {
            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            // Get current date and time
            String[] dateAndTime = getCurrentDateTime();
            String date = dateAndTime[0];
            String time = dateAndTime[1];

            // Create transaction object
            PataPayTransaction deposit = new PataPayTransaction(date, time, description, vendor, amount);

            // Save transaction to CSV file
            writeTransactionToFile(deposit);
            System.out.println("Deposit saved successfully!");
        } catch (Exception e) {
            System.out.println("Error saving deposit. Please try again.");
        }
    }

    // ------------------ PAYMENTS ------------------
    public static void makePayment() {
        try {
            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            amount = -1 * amount; // Payments are negative amounts

            // Get current date and time
            String[] dateAndTime = getCurrentDateTime();
            String date = dateAndTime[0];
            String time = dateAndTime[1];

            // Create transaction object
            PataPayTransaction payment = new PataPayTransaction(date, time, description, vendor, amount);

            // Save transaction to CSV file
            writeTransactionToFile(payment);
            System.out.println("Payment saved successfully!");
        } catch (Exception e) {
            System.out.println("Error saving payment. Please try again.");
        }
    }

    // ------------------ CSV FILE ------------------
    // Save a transaction to the CSV file
    private static void writeTransactionToFile(PataPayTransaction transaction) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(transaction.getDate() + "|" +
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    // Get current date and time as strings
    private static String[] getCurrentDateTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm:ss");

        return new String[]{date.format(dateFmt), time.format(timeFmt)};
    }

    // ------------------ LEDGER ------------------
    public static void runLedger() {
        boolean running = true;

        while (running) {
            // Display ledger menu
            System.out.println("**********");
            System.out.println("LEDGER SCREEN");
            System.out.println("**********");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.println("**********");

            System.out.print("Please make your selection from the menu: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            // Perform action based on user choice
            switch (choice) {
                case "A":
                    displayAllTransactions();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    runReports();
                    break;
                case "H":
                    running = false; // Go back to home screen
                    break;
                default:
                    System.out.println("INVALID CHOICE. Please enter A, D, P, R, or H.");
            }
        }
    }

    // ------------------ LOAD TRANSACTIONS ------------------
    // Load all transactions from the CSV file as objects
    private static ArrayList<PataPayTransaction> loadTransactions() {
        ArrayList<PataPayTransaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split("\\|"); // Split CSV line by '|'
                    if (parts.length != 5) continue; // Skip invalid lines

                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4].trim());

                    PataPayTransaction t = new PataPayTransaction(date, time, description, vendor, amount);
                    transactions.add(t);
                } catch (Exception e) {
                    System.out.println("Skipping invalid line in CSV: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions file.");
        }

        // Show newest transactions first
        Collections.reverse(transactions);
        return transactions;
    }

    // ------------------ DISPLAY TRANSACTIONS ------------------
    private static void displayAllTransactions() {
        System.out.println("\n***** ALL TRANSACTIONS *****");
        for (PataPayTransaction t : loadTransactions()) {
            System.out.println(t);
        }
    }

    private static void displayDeposits() {
        System.out.println("\n***** DEPOSITS *****");
        for (PataPayTransaction t : loadTransactions()) {
            if (t.getAmount() > 0) System.out.println(t);
        }
    }

    private static void displayPayments() {
        System.out.println("\n***** PAYMENTS *****");
        for (PataPayTransaction t : loadTransactions()) {
            if (t.getAmount() < 0) System.out.println(t);
        }
    }

    // ------------------ REPORTS ------------------
    private static void runReports() {
        boolean running = true;

        while (running) {
            System.out.println("\n***** Reports Menu *****");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayMonthToDate();
                    break;
                case "2":
                    displayPreviousMonth();
                    break;
                case "3":
                    displayYearToDate();
                    break;
                case "4":
                    displayPreviousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    running = false; // Back to ledger menu
                    break;
                default:
                    System.out.println("Invalid choice. Enter 1-5 or 0.");
            }
        }
    }

    // ------------------ REPORT FILTER METHODS ------------------
    private static void displayMonthToDate() {
        System.out.println("\n***** MONTH TO DATE *****");
        ArrayList<PataPayTransaction> transactions = loadTransactions();

        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime now = LocalDateTime.now();

        for (PataPayTransaction t : transactions) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(t.getDate() + " " + t.getTime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (!dateTime.isBefore(startOfMonth) && !dateTime.isAfter(now)) System.out.println(t);
            } catch (Exception e) {
                // Skip invalid date/time
            }
        }
    }

    private static void displayPreviousMonth() {
        System.out.println("\n***** PREVIOUS MONTH *****");
        ArrayList<PataPayTransaction> transactions = loadTransactions();

        LocalDate firstDayCurrentMonth = LocalDate.now().withDayOfMonth(1);
        LocalDateTime startPrev = firstDayCurrentMonth.minusMonths(1).atStartOfDay();
        LocalDateTime endPrev = firstDayCurrentMonth.minusDays(1).atTime(23, 59, 59);

        for (PataPayTransaction t : transactions) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(t.getDate() + " " + t.getTime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (!dateTime.isBefore(startPrev) && !dateTime.isAfter(endPrev)) System.out.println(t);
            } catch (Exception e) { }
        }
    }

    private static void displayYearToDate() {
        System.out.println("\n***** YEAR TO DATE *****");
        ArrayList<PataPayTransaction> transactions = loadTransactions();

        LocalDateTime startYear = LocalDate.now().withDayOfYear(1).atStartOfDay();
        LocalDateTime now = LocalDateTime.now();

        for (PataPayTransaction t : transactions) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(t.getDate() + " " + t.getTime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (!dateTime.isBefore(startYear) && !dateTime.isAfter(now)) System.out.println(t);
            } catch (Exception e) { }
        }
    }

    private static void displayPreviousYear() {
        System.out.println("\n***** PREVIOUS YEAR *****");
        ArrayList<PataPayTransaction> transactions = loadTransactions();

        LocalDate firstDayCurrentYear = LocalDate.now().withDayOfYear(1);
        LocalDateTime startPrev = firstDayCurrentYear.minusYears(1).atStartOfDay();
        LocalDateTime endPrev = firstDayCurrentYear.minusDays(1).atTime(23, 59, 59);

        for (PataPayTransaction t : transactions) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(t.getDate() + " " + t.getTime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (!dateTime.isBefore(startPrev) && !dateTime.isAfter(endPrev)) System.out.println(t);
            } catch (Exception e) { }
        }
    }

    private static void searchByVendor() {
        System.out.print("Enter vendor name to search: ");
        String vendorSearch = scanner.nextLine().trim().toLowerCase();

        System.out.println("\n***** SEARCH RESULTS FOR VENDOR: " + vendorSearch + " *****");
        ArrayList<PataPayTransaction> transactions = loadTransactions();
        boolean found = false;

        for (PataPayTransaction t : transactions) {
            if (t.getVendor().toLowerCase().contains(vendorSearch)) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) System.out.println("No transactions found for this vendor.");
    }
}
