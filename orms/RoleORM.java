package orms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Role;

public class RoleORM extends ORM<Role>{
    public RoleORM() {
        tableName = "roles";
    }

    @Override
    public Role add(Role role) {
        try (var stmt = connection.prepareStatement("INSERT INTO " + tableName + " VALUES(NULL,?)", 
            Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, role.getRole());
        stmt.execute();
        var rs = stmt.getGeneratedKeys();
        if (rs.next())
            role.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return role;
  }

    @Override
    public ArrayList<Role> listAll(){
        String query = "SELECT * FROM " + tableName;
        return this.rawQueryList(query);
    }

    @Override
    public boolean delete(int id) {
        try (var stmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id=" + id)) {
            stmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // @Override
    // public void update(Role role) {
    //     try (var stmt = connection.prepareStatement("UPDATE " + tableName +
    //         " SET role = ? WHERE id = ?")) {
    //     stmt.setString(1, role.getRole());
    //     stmt.setInt(2, role.getId());
    //     stmt.execute();
    //     } catch (Exception e) {
    //     System.out.println(e.getMessage());
    //     }
    // }

    @Override
    public void update(Role r){
        var query = "UPDATE " + tableName +
                    " SET role = '" + r.getRole() + "'" +
                    " WHERE id = " + r.getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }      
        System.out.println();  
    }
    
    @Override
    public ArrayList<Role> rawQueryList(String query) {
        ArrayList<Role> img = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            img.add(new Role(rs.getInt("id"),
                rs.getString("role")));
        }
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
        return img;
    }

    public static void main(String args[]) throws Exception{
        RoleORM roleORM = new RoleORM();
        
        //add role
        Role role1 = new Role(1, "Admin");
        Role role2 = new Role(2, "Customer");

        roleORM.add(role1);
        roleORM.add(role2);

        // list all
        for(var r : roleORM.listAll()){
            System.out.println("\n\tId\t:\t" + r.getId() +
                                "\n\tRole\t:\t" + r.getRole());
        }
        System.out.println();

        //remove
        roleORM.delete(1);

        //update
        var newRole = new Role(2, "Admin");
        roleORM.update(newRole);

        //List rows by raw query
        for (Role r : roleORM.rawQueryList("select * from roles where id= 2")) {
            System.out.println("\n\tID: " + r.getId() + 
                            "\n\tRole: " +r.getRole());
        }       
    }
}
