package orms;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


import models.Hotel;
import orms.HotelORM;


public class HotelORM extends ORM<Hotel>{
    public HotelORM(){
        tableName = "hotels";
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public Hotel add(Hotel h){
        try(var statement = connection.prepareStatement("INSERT INTO " + tableName + " VALUES(NULL,?,?,?,?,?,?)",
        Statement.RETURN_GENERATED_KEYS)){
            short star = h.getStars();
            statement.setString(1, h.getHotel());
            statement.setInt(2, h.getCountry().getId());
            statement.setInt(3, h.getCity().getId());
            statement.setShort(4, star);
            statement.setDouble(5, h.getCost());
            statement.setString(6, h.getInfo());
            statement.execute();

            var rs = statement.getGeneratedKeys();
            if(rs.next())
                h.setId(rs.getInt(1));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage()); //get error message
        }
        return h;
    }
    @Override
    public ArrayList<Hotel> listAll() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        CountryORM countryORM = new CountryORM();
        CityORM cityORM = new CityORM();


        try(var stmt = connection.createStatement()){
            ResultSet result = stmt.executeQuery("SELECT * FROM "+ tableName);

            while (result.next()) {
                var country = countryORM.rawQueryList("SELECT * FROM countries WHERE id=" + result.getInt("countryid")).get(0);
                var city = cityORM.rawQueryList("SELECT * FROM cities WHERE id=" + result.getInt("cityid")).get(0);
        
                hotels.add(new Hotel(result.getInt("id"),
                        result.getString(2),
                        country,
                        city,
                        result.getShort(5),
                        result.getDouble(6),
                        result.getString(7)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return hotels;
    }

    @Override
    public boolean delete(int id){
        try(var stmt = connection.prepareStatement("DELETE FROM " + tableName +" WHERE id="+id)){
            stmt.execute();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Hotel h) {
        try (var stmt = connection.prepareStatement("UPDATE `" + tableName +
            "` SET `hotel` = ?, `countryid` = ?, `cityid` = ?, " +
            "`stars` = ?, `cost` = ?, `info` = ? WHERE `id` = ?")) {
        stmt.setString(1, h.getHotel());
        stmt.setInt(2, h.getCountry().getId());
        stmt.setInt(3, h.getCity().getId());
        stmt.setShort(4, h.getStars());
        stmt.setDouble(5, h.getCost());
        stmt.setString(6, h.getInfo());
        stmt.setInt(7, h.getId());
        stmt.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    }

    @Override
    public ArrayList<Hotel> rawQueryList(String query) {
        ArrayList<Hotel> hotels = new ArrayList<>();

        try (var stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            CountryORM countries = new CountryORM();
            CityORM cities = new CityORM();
            while (rs.next()) {
              String country = "SELECT * FROM countries WHERE id = " + rs.getInt(3);
              String city = "SELECT * FROM cities WHERE id = " + rs.getInt(4);
              
              hotels.add(new Hotel(
                  rs.getInt(1),
                  rs.getString(2),
                  countries.rawQueryList(country).get(0),
                  cities.rawQueryList(city).get(0),
                  rs.getShort(5),
                  rs.getDouble(6),
                  rs.getString(7)));
            }
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        return hotels;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelORM hotelORM = new HotelORM();

        CityORM cityORM = new CityORM();
        CountryORM countryORM = new CountryORM();

        // var country = countryORM.rawQueryList("SELECT * FROM countries WHERE id=" +1).get(0);
        // var city = cityORM.rawQueryList("SELECT * FROM cities WHERE id=" +1).get(0);

        int opt;
        do{
            System.out.println("""
                \n\t1. List all hotels
                \t2. Add new hotel
                \t3. Remove a hotel by id
                \t4. Update a hotel by id
                \t5. List rows by raw query
                \t0. Exit from hotel listing    
                """);
            
            System.out.print("\t\n=> Choose an option: ");

            while(!sc.hasNextInt()){
                System.out.print("\t**Input value only.");
                sc.nextLine();
            }
            opt = sc.nextInt();
            switch(opt){
                case 1:
                    
                    for(var h : hotelORM.listAll()){
                        System.out.println("\n\tHotelId\t\t:\t" + h.getId() +
                                            "\n\tHotel\t\t:\t" + h.getHotel() +
                                            "\n\tCountryId\t:\t" + h.getCountry().getId() + 
                                            "\n\tCityId\t\t:\t" + h.getCity().getId() +
                                            "\n\tStars\t\t:\t" + h.getStars() +
                                            "\n\tCost\t\t:\t" + h.getCost() +
                                            "\n\tInfo\t\t:\t" + h.getInfo());
                    }
                    System.out.println();
                    break;
                case 2:
                try {
                    // System.out.print("\n\tAdd new hotel: ");
                    // String newHotel = sc.nextLine();
                    // newHotel = sc.nextLine();

                    // System.out.print("\tAdd new hotel stars: ");
                    // short newStars = sc.nextShort();

                    // System.out.print("\tAdd new hotel cost: ");
                    // double newCost = sc.nextDouble();

                    // System.out.print("\tAdd new hotel info: ");
                    // String newInfo = sc.nextLine();
                    // newInfo = sc.nextLine();


                    // var hotel = new Hotel(0, newHotel, country, city, newStars, newCost, newInfo);

                    short stars = 4;
                    // Hotel hotel = new Hotel(2, "Osaka Teikoku", countryORM.listAll().get(2), cityORM.listAll().get(2), stars, 270, "Osaka Teikoku Hotel is a comfortable property conveniently located near the centre of Osaka");
                    Hotel hotel = new Hotel(3, "Koko", countryORM.listAll().get(2), cityORM.listAll().get(2), stars, 270, "Osaka Teikoku Hotel is a comfortable property conveniently located near the centre of Osaka");


                    hotelORM.add(hotel);
                    System.out.println("\t=> New added city id: "+hotel.getId()+"\n");
                    
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
                    
                case 3:
                    System.out.print("\n\tRemove a country by id: ");
                    hotelORM.delete(sc.nextInt());
                    System.out.println("\tRemove successfully!!");
                    break;
                case 4:
                    System.out.println("""
                        \n\tUpdate table country
                        \t--------------------""");

                        // System.out.print("\tInput ID to update: ");
                        // int Id = sc.nextInt();
                        // System.out.print("\tInput new city: ");
                        // newC = sc.nextLine();
                        // newC = sc.nextLine();
                        
                        // var newCity = new City(1, newC, country, null);
                        // cityORM.update(newCity);
                        short stars = 4;
                        Hotel newHotel = new Hotel(1, "Raffles", countryORM.listAll().get(1), cityORM.listAll().get(3), stars, 200, "Raffles Hotel Le Royal invites you to enjoy an unforgettable stay at its iconic luxury hotel just off Phnom Penhs main boulevard near foreign embassies");
                        hotelORM.update(newHotel);
                        System.out.println("\t=> Update successfully!!");
                    break;
                case 5:
                    System.out.println("""
                            \n\tList rows by raw query
                            \t----------------------
                            """);
                        System.out.print("\tInput ID to list row: ");
                        int Id = sc.nextInt();
                        for (Hotel h : hotelORM.rawQueryList("select * from hotels where id= "+Id)) {
                            System.out.println("\n\tHotelID: " + h.getId() + 
                                    "\n\tHotel: " +h.getHotel() +
                                    "\n\tCountry: " + h.getCountry().getCountry() +
                                    "\n\tCity: " + h.getCity().getCity() +
                                    "\n\tStars: " +h.getStars() +
                                    "\n\tCost : " +h.getCost() +
                                    "\n\tInfo : " +h.getInfo());
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
