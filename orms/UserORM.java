package orms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.User;

public class UserORM extends ORM<User>{
    public UserORM() {
        tableName = "users";
    }

    @Override
    public User add(User user) {
        try (var stmt = connection.prepareStatement("INSERT INTO " + tableName
            + " VALUES(NULL,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPass());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getRole().getId());
            stmt.setShort(5, user.getDiscount());
            stmt.setString(6, user.getAvatar());
            stmt.execute();

        var rs = stmt.getGeneratedKeys();
        if (rs.next())
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> listAll() {
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

    @Override
    public void update(User user) {
        try (var stmt = connection.prepareStatement("UPDATE " + tableName +
            " SET username = ?, pass = ?, email = ?, " +
            "roleid = ?, discount = ?, avatar = ? WHERE id = ?")) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPass());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getRole().getId());
            stmt.setShort(5, user.getDiscount());
            stmt.setString(6, user.getAvatar());
            stmt.setInt(7, user.getId());
            stmt.execute();
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<User> rawQueryList(String query) {
        ArrayList<User> user = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
        ResultSet rs = stmt.executeQuery(query);
        RoleORM roles = new RoleORM();
        while (rs.next()) {
            String getRole = "SELECT * FROM roles WHERE id = " + rs.getInt(5);
            user.add(new User(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("pass"),
                rs.getString("email"),
                roles.rawQueryList(getRole).get(0),
                rs.getShort("discount"),
                rs.getString("avatar")));
        }
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
        return user;
    }

    public static void main(String[] args) {
        UserORM userORM = new UserORM();
        RoleORM roleORM = new RoleORM();

        //add user
        short discount = 70;
        User user = new User(0, "Sopheak", "********", "ssopheak2002@gmain.com", roleORM.listAll().get(1), discount, "images/sopheak.jpg");
        userORM.add(user);

        // list all
        for(var u : userORM.listAll()){
            System.out.println("\n\tId\t\t:\t" + u.getId() +
                                "\n\tUsername\t\t:\t" + u.getUsername() +
                                "\n\tPass\t\t:\t" + u.getPass() +
                                "\n\tEmail\t\t:\t" + u.getEmail() +
                                "\n\tRole ID\t:\t" + u.getRole().getId() +
                                "\n\tDiscount\t\t:\t" + u.getDiscount() + "%" +
                                "\n\tAvatar\t:\t" + u.getAvatar());
        }
        System.out.println();

        //update
        // var newUser = new User(2, "minea", "********", "bopha111@gmain.com", roleORM.listAll().get(1), discount, "images/minea.jpg");
        // userORM.update(newUser);

        //remove
        // userORM.delete(2);

        //List rows by raw query
        // for (User u : userORM.rawQueryList("select * from users where id= 1")) {
        //     System.out.println("\n\tUser ID: " + u.getId() + 
        //                     "\n\tUsername: " + u.getUsername() +
        //                     "\n\tPass: " + u.getPass() +
        //                     "\n\tEmail: " + u.getEmail() +
        //                     "\n\tRole ID: " + u.getRole().getId() +
        //                     "\n\tDiscount: " + u.getDiscount() +
        //                     "\n\tAvatar : " +u.getAvatar());
        // }
    }
}
