package by.epam.kusanov.restaurant.bean;

import java.io.Serializable;

public class Role implements Serializable {
    private int roleId;
    private String roleName;

    public Role() {
        this.roleId = 2;
        this.roleName = "visitor";
    }

    public Role(int roleId, String user_role) {
        this.roleId = roleId;
        this.roleName = user_role;
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
