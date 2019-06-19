package com.earthdefensesystem.tiemendo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Transaction implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("inputs")
    @Expose
    private List<TransactionItem> inputs = null;
    @SerializedName("personnel")
    @Expose
    private String personnel;
    @SerializedName("total")
    @Expose
    private Double total;

    public Transaction(Integer id, String type, String date, List<TransactionItem> inputs, String personnel, Double total) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.inputs = inputs;
        this.personnel = personnel;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TransactionItem> getInputs() {
        return inputs;
    }

    public void setInputs(List<TransactionItem> inputs) {
        this.inputs = inputs;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
