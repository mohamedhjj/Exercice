package com.bank.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance = 0.0;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StatementEntry> statements = new ArrayList<>();

    // Getters and setters
    public Long getId() { return id; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public List<StatementEntry> getStatements() { return statements; }
    public void setStatements(List<StatementEntry> statements) { this.statements = statements; }
}