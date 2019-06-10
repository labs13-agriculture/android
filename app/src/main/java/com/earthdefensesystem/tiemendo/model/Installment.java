package com.earthdefensesystem.tiemendo.model;

import java.util.Date;

public class Installment {
    private long installmentid;
    private double amountpaid;
    private Date datepaid;
    private String mode;
    private String officer;
    private Client client;

    public Installment(long installmentid, double amountpaid, Date datepaid, String mode, String officer, Client client) {
        this.installmentid = installmentid;
        this.amountpaid = amountpaid;
        this.datepaid = datepaid;
        this.mode = mode;
        this.officer = officer;
        this.client = client;
    }

    public long getInstallmentid() {
        return installmentid;
    }

    public void setInstallmentid(long installmentid) {
        this.installmentid = installmentid;
    }

    public double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public Date getDatepaid() {
        return datepaid;
    }

    public void setDatepaid(Date datepaid) {
        this.datepaid = datepaid;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
