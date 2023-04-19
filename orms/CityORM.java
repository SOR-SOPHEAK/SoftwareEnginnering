package orms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.sql.Statement;

import models.City;
import models.Country;
import orms.CountryORM;

public class CityORM extends ORM<City>{
    public CityORM(){
        tableName = "cities";
    }

    @Override
    public ArrayList<City> listAll(){
        ArrayList<City> cities = new ArrayList<>();
        CountryORM countryORM = new CountryORM();

        try(var stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);

            while (rs.next()) {
                var country = countryORM.rawQueryList("SELECT * FROM countries WHERE id=" + rs.getInt("countryid")).get(0);

                cities.add(new City(rs.getInt("id"),
                        rs.getString(2),
                        country,
                        rs.getString(4)));
            }
        } catch (Exception e) {e.printStackTrace();}
        return cities;
    }

    @Override
    public City add(City t){

        try(var statement = connection.prepareStatement("INSERT INTO " + tableName + " VALUES(NULL,?,?,?)",
        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, t.getCity());
            statement.setInt(2, t.getCountry().getId());
            statement.setString(3, t.getUcity());
            statement.execute();
            var rs = statement.getGeneratedKeys();
            if (rs.next())
                t.setId(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
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
    public void update(City t) {
        var query = "UPDATE " + tableName +
        " SET city = '" + t.getCity() + "'" +
        " , countryid = " + t.getCountry().getId() +
        " , ucity = " + t.getUcity() + "" +
        " WHERE id = " + t.getId();

        try (var stmt = connection.prepareStatement(query)) {
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    @Override
    public ArrayList<City> rawQueryList(String query) {
        ArrayList<City> cities = new ArrayList<>();

        try (var stmt = connection.createStatement()) {
            stmt.executeQuery(query);

            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                var country = new CountryORM()
                        .rawQueryList("SELECT * FROM countries WHERE id=" + result.getInt("countryid")).get(0);

                cities.add(new City(result.getInt("id"),
                        result.getString(2),
                        country,
                        result.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CityORM cityORM = new CityORM();
        CountryORM countryORM = new CountryORM();
        var country = countryORM.rawQueryList("SELECT * FROM countries WHERE id=" + 1).get(0);

        int opt;
        do{
            System.out.println("""
                \n\t1. List all cities
                \t2. Add new city
                \t3. Remove a city by id
                \t4. Update a city by id
                \t5. List rows by raw query
                \t0. Exit from city listing    
                """);
            
            System.out.print("\t\n=> Choose an option: ");

            while(!sc.hasNextInt()){
                System.out.print("\t**Input value only.");
                sc.nextLine();
            }
            opt = sc.nextInt();
            switch(opt){
                case 1:
                // System.out.println("""
                //                         \n\t=================================================================
                //                         \t| CityId |      City      | CountryId |    Country    | Uinque City |
                //                         \t=================================================================
                //                         """);
                        
                    for(var ct : cityORM.listAll()){
                        System.out.println("\n\tCityId\t\t:\t" + ct.getId() +
                                            "\n\tCity\t\t:\t" + ct.getCity() +
                                            "\n\tCountryId\t:\t" + ct.getCountry().getId() + 
                                            "\n\tCountry\t\t:\t" + ct.getCountry().getCountry() +
                                            "\n\tUnique City\t:\t" + ct.getUcity());
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("\n\tAdd new city: ");
                    String newC = sc.nextLine();
                    newC = sc.nextLine();
                    // System.out.print("\tAdd new unique city: ");
                    // String newUC = sc.nextLine();

                    var city = new City(0, newC, country, null);
                    cityORM.add(city);
                    System.out.println("\t=> New added city id: "+city.getId()+"\n");
                    break;
                case 3:
                    System.out.print("\n\tRemove a country by id: ");
                    cityORM.delete(sc.nextInt());
                    System.out.println("\tRemove successfully!!");
                    break;
                case 4:
                    System.out.println("""
                        \n\tUpdate table country
                        \t--------------------""");

                        System.out.print("\tInput ID to update: ");
                        int Id = sc.nextInt();
                        System.out.print("\tInput new city: ");
                        newC = sc.nextLine();
                        newC = sc.nextLine();
                        // System.out.print("\tInput new unique city: ");
                        // String newuC = sc.nextLine();

                        // var newCity = new City(Id, newC, country, newuC);
                        
                        var newCity = new City(Id, newC, country, null);
                        cityORM.update(newCity);
                        System.out.println("\t=> Update successfully!!");
                    break;
                case 5:
                    System.out.println("""
                            \n\tList rows by raw query
                            \t----------------------
                            """);
                        System.out.print("\tInput ID to list row: ");
                        Id = sc.nextInt();
                        for (City c : cityORM.rawQueryList("select * from cities where id="+Id)) {
                            System.out.println("\n\tCityID: " + c.getId() + 
                                    "\n\tCity: " + c.getCity() + 
                                    "\n\tcountryID: " + c.getCountry().getId() + 
                                    "\n\tCountry: " + c.getCountry().getCountry() + 
                                    "\n\tUnique City: " + c.getUcity());
                    }
                    break;
                case 0:
                    System.out.println("\tProgram exit!!");
                    System.exit(1);
                    break;
            }
        }while(opt != 0);
    }
}
