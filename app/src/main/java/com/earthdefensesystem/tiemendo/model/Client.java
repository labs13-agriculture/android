package com.earthdefensesystem.tiemendo.model;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable {
    private long clientid;
    private String name;
    private boolean islead;
    private String type;
    private List<Transaction> transactions;
    private List<Installment> installments;

    public Client(long clientid, String name, boolean islead, String type, List<Transaction> transactions, List<Installment> installments) {
        this.clientid = clientid;
        this.name = name;
        this.islead = islead;
        this.type = type;
        this.transactions = transactions;
        this.installments = installments;
    }

    public long getClientid() {
        return clientid;
    }

    public void setClientid(long clientid) {
        this.clientid = clientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIslead() {
        return islead;
    }

    public void setIslead(boolean islead) {
        this.islead = islead;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }
}
