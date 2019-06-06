package com.earthdefensesystem.tiemendo.model;

public class TransactionItem {
    private long id;
    private int quantity;
    private double unitprice;
    private ItemType itemtype;
    private Transaction transaction;

    public TransactionItem(long id, int quantity, double unitprice, ItemType itemtype, Transaction transaction) {
        this.id = id;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.itemtype = itemtype;
        this.transaction = transaction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public ItemType getItemtype() {
        return itemtype;
    }

    public void setItemtype(ItemType itemtype) {
        this.itemtype = itemtype;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
