package com.example.codegymfoods.model.login;

import javax.persistence.*;

@Entity
@Table(name = "app_role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "app_role_uk", columnNames = "role_name") })
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;

    public AppRole() {
    }

    public AppRole(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}