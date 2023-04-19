package models;

import exception.InvalidRoleException;

public class Role extends Model{
    private String role;

    public Role(int id) {
        super(id);
    }

    public Role(int id, String role) {
        super(id);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role==null || role.isBlank()) throw new InvalidRoleException();
        this.role = role;
    }
}
