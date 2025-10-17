package com.pluralsight;

public class PataPayTransaction {
    // Fields
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    // Constructor to create a new transaction object
    public PataPayTransaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Getter for date
    public String getDate() {
        return date;
    }

    // Getter for time
    public String getTime() {
        return time;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for vendor
    public String getVendor() {
        return vendor;
    }

    // Getter for amount
    public double getAmount() {
        return amount;
    }

    // This method returns a formatted string representation of the transaction
    @Override
    public String toString() {
        return "***** TRANSACTION *****\n" +
                "Date = " + date + "\n" +
                "Time = " + time + "\n" +
                "Description = " + description + "\n" +
                "Vendor = " + vendor + "\n" +
                "Amount = " + amount + "\n" +
                "********************\n";
    }
}
