package com.bank.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StatementEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private double amount;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public StatementEntry() {}

    public StatementEntry(LocalDateTime date, double amount, double balance, Account account) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
        this.account = account;
    }

    // Getters and setters
    public Long getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public double getAmount() { return amount; }
    public double getBalance() { return balance; }
    public Account getAccount() { return account; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setAccount(Account account) { this.account = account; }
}