package com.earthdefensesystem.tiemendo.model;

import java.io.Serializable;
import java.util.List;

public class CropType implements Serializable {
    private long croptypeid;
    private String cropname;
    private Boolean active;
    private List<Yield> yields;

    public CropType(long croptypeid, String cropname, Boolean active, List<Yield> yields) {
        this.croptypeid = croptypeid;
        this.cropname = cropname;
        this.active = active;
        this.yields = yields;
    }

    public long getCroptypeid() {
        return croptypeid;
    }

    public void setCroptypeid(long croptypeid) {
        this.croptypeid = croptypeid;
    }

    public String getCropname() {
        return cropname;
    }

    public void setCropname(String cropname) {
        this.cropname = cropname;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Yield> getYields() {
        return yields;
    }

    public void setYields(List<Yield> yields) {
        this.yields = yields;
    }
}
