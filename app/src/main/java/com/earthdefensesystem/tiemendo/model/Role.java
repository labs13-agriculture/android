package com.earthdefensesystem.tiemendo.model;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private long roleid;
    private String name;
    private List<UserRoles> userroles;

    public Role(long roleid, String name, List<UserRoles> userroles) {
        this.roleid = roleid;
        this.name = name;
        this.userroles = userroles;
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRoles> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles) {
        this.userroles = userroles;
    }
}
