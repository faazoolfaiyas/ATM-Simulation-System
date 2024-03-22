package org.example.atm_simulation_system;

import java.time.LocalDate;

public class AccountData {
    private String ownerName;
    private String accountNumber;
    private double amount;
    private int cvv;
    private int pin;
    private Integer pinTimes;

    public AccountData(String ownerName, String accountNumber, double amount, int cvv, int pin, Integer pinTimes) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.cvv = cvv;
        this.pin = pin;
        this.pinTimes = pinTimes;
    }

    // Getters for accessing the data
    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public int getCvv() {
        return cvv;
    }

    public int getPin() {
        return pin;
    }

    public Integer getPinTimes() {
        return pinTimes;
    }
}
