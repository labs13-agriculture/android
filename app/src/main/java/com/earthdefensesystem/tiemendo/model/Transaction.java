package com.earthdefensesystem.tiemendo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Transaction implements Serializable {
    private long transactionid;
    private String type;
    private Date date;
    private List<TransactionItem> transactionitems;
    private String personnel;
    private Client client;

    public Transaction(long transactionid, String type, Date date, List<TransactionItem> transactionitems, String personnel, Client client) {
        this.transactionid = transactionid;
        this.type = type;
        this.date = date;
        this.transactionitems = transactionitems;
        this.personnel = personnel;
        this.client = client;
    }

    public long getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(long transactionid) {
        this.transactionid = transactionid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<TransactionItem> getTransactionitems() {
        return transactionitems;
    }

    public void setTransactionitems(List<TransactionItem> transactionitems) {
        this.transactionitems = transactionitems;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
