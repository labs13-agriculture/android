package com.earthdefensesystem.tiemendo.model;

import java.util.List;

public class ItemType {
    private long itemtypeid;
    private String name;
    private List<TransactionItem> transactionitems;

    public ItemType(long itemtypeid, String name, List<TransactionItem> transactionitems) {
        this.itemtypeid = itemtypeid;
        this.name = name;
        this.transactionitems = transactionitems;
    }

    public long getItemtypeid() {
        return itemtypeid;
    }

    public void setItemtypeid(long itemtypeid) {
        this.itemtypeid = itemtypeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TransactionItem> getTransactionitems() {
        return transactionitems;
    }

    public void setTransactionitems(List<TransactionItem> transactionitems) {
        this.transactionitems = transactionitems;
    }
}
