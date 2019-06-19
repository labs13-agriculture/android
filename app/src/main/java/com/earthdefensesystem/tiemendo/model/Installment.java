package com.earthdefensesystem.tiemendo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Installment implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("amountPaid")
    @Expose
    private Double amountPaid;
    @SerializedName("datePaid")
    @Expose
    private String datePaid;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("officer")
    @Expose
    private String officer;

    public Installment(Integer id, Double amountPaid, String datePaid, String mode, String officer) {
        this.id = id;
        this.amountPaid = amountPaid;
        this.datePaid = datePaid;
        this.mode = mode;
        this.officer = officer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }
}
