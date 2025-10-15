package com.pluralsight;

public class PataPayTransactions {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public PataPayTransactions(String date, double amount, String vendor, String description, String time) {
        this.date = date;
        this.amount = amount;
        this.vendor = vendor;
        this.description = description;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }
}
