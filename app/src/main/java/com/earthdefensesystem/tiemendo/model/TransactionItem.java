package com.earthdefensesystem.tiemendo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TransactionItem implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("unitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("item")
    @Expose
    private Object item;

    public TransactionItem(Integer id, Integer quantity, Double unitPrice, Object item) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
