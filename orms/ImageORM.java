package orms;

import java.io.InputStream;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import models.Image;

public class ImageORM extends ORM<Image>{
    public ImageORM(){
        tableName = "images";
    }

    @Override
    public Image add(Image img) {
        try (var stmt = connection.prepareStatement("INSERT INTO " + tableName + " VALUES(NULL,?,?)", 
            Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, img.getHotel().getId());
            stmt.setString(2, img.getImagepath());
            stmt.execute();

        var rs = stmt.getGeneratedKeys();
        if (rs.next())
            img.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return img;
  }

  @Override
  public ArrayList<Image> listAll() {
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
    public void update(Image img) {
        try (var stmt = connection.prepareStatement("UPDATE " + tableName +
            " SET hotelid = ?, imagepath = ? WHERE id = ?")) {
            stmt.setInt(1, img.getHotel().getId());
            stmt.setString(2, img.getImagepath());
            stmt.setInt(3, img.getId());
            stmt.execute();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Image> rawQueryList(String query) {
        ArrayList<Image> ar = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            HotelORM hotels = new HotelORM();
        while (rs.next()) {
            String queryHotel = "SELECT * FROM hotels WHERE id = " + rs.getInt(2);
            ar.add(new Image(rs.getInt("id"),
                hotels.rawQueryList(queryHotel).get(0), rs.getString(3)));
        }
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return ar;
    }

    public static void main(String[] args) {
        ImageORM imgORM = new ImageORM();
        HotelORM hotelORM = new HotelORM();

        //add image
        Image img1 = new Image(1, hotelORM.listAll().get(1), "images/Raffles.jpg");
        imgORM.add(img1);

        // list all
        for(var i : imgORM.listAll()){
            System.out.println("\n\tId\t\t:\t" + i.getId() +
                                "\n\tHotel ID\t:\t" + i.getHotel().getId() +
                                "\n\tImage Path\t:\t" + i.getImagepath());
        }
        System.out.println();

        //update
        var newImg = new Image(1, hotelORM.listAll().get(1), "images/Raffles1.jpg");
        imgORM.update(newImg);

        //remove
        imgORM.delete(1);

        //List rows by raw query
        for (Image i : imgORM.rawQueryList("select * from images where id= 2")) {
            System.out.println("\n\tID: " + i.getId() + 
                                "\n\tHotel ID: " +i.getHotel().getId() +
                                "\n\tImage Path: " +i.getImagepath());
        }  
    }
}
