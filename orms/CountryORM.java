package orms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import models.Country;

public class CountryORM extends ORM<Country>{
    public CountryORM(){
        tableName = "countries";
    }

    @Override
    public ArrayList<Country> listAll(){
        ArrayList<Country> ar = new ArrayList<>();
        try(var stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);
            while(rs.next()){
                ar.add(new Country(rs.getInt("id"),
                rs.getString(2)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ar;
    }

    @Override 
    public Country add(Country t){
        try(var stmt = connection.prepareStatement("INSERT INTO "+tableName
            +" VALUES(NULL,?)", Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, t.getCountry());
            stmt.execute();
            var rs = stmt.getGeneratedKeys();
                
            if(rs.next()) t.setId(rs.getInt(1));
        }catch(SQLException e){e.printStackTrace();}
        return t;
    }

    @Override
    public boolean delete(int id){
        try(var stmt = connection.prepareStatement("DELETE FROM "+tableName+" WHERE id="+id)){
            stmt.execute();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Country t){
        var query = "UPDATE " + tableName +
                    " SET country = '" + t.getCountry() + "'" +
                    " WHERE id = " + t.getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }      
        System.out.println();  
    }

    @Override 
    public ArrayList<Country> rawQueryList(String query){
        ArrayList<Country> countries = new ArrayList<>();

        try (var stmt = connection.createStatement()) {
            stmt.executeQuery(query);

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                countries.add(new Country(rs.getInt("id"),
                    rs.getString(2)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return countries;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CountryORM countryORM = new CountryORM();

        int opt;
        do{
            System.out.println("""
                \n\t1. List all countries
                \t2. Add new country
                \t3. Remove a country by id
                \t4. Update a country by id
                \t5. List rows by raw query
                \t0. Exit from country listing    
                """);
            
            System.out.print("\t\n=> Choose an option: ");

            while(!sc.hasNextInt()){
                System.out.print("\t**Input value only.");
                sc.nextLine();
            }
            opt = sc.nextInt();
            switch(opt){
                case 1:
                System.out.println("""
                    \n\t  List all countries
                    \t----------------------""");
                    for(var c : countryORM.listAll()){
                        System.out.println("\tId: "+c.getId()+", Country: "+c.getCountry());
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("\n\tAdd new country: ");
                    String cName = sc.nextLine();
                    cName = sc.nextLine();
                    var country = new Country(0, cName);
                    countryORM.add(country);
                    System.out.println("\t=> New added country id: "+country.getId()+"\n");
                    break;
                case 3:
                    System.out.print("\n\tRemove a country by id: ");
                    countryORM.delete(sc.nextInt());
                    System.out.println("\tRemove successfully!!");
                    break;
                case 4:
                    System.out.println("""
                        \n\tUpdate table country
                        \t--------------------""");

                        System.out.print("\tInput ID to update: ");
                        int Id = sc.nextInt();
                        System.out.print("\tInput new country: ");
                        String newC = sc.nextLine();
                        newC = sc.nextLine();
                        var newCountry = new Country(Id, newC);
                        countryORM.update(newCountry);
                        System.out.println("\t=> Update successfully!!");
                    break;
                case 5:
                    System.out.println("""
                            \n\tList rows by raw query
                            \t----------------------
                            """);
                        System.out.print("\t=> Input ID to list row: ");
                        Id = sc.nextInt();

                        System.out.println("\t-------------+-------------------");
                    System.out.println("\t|\tID\tCountry\t\t|");
                    System.out.println("\t=============+===================");
                    for(Country c : countryORM.rawQueryList("select * from countries where id="+Id)){
                        System.out.println("\t|\t"+c.getId() +
                                        "\t"+c.getCountry() + "\t|" +
                                        "\n\t-------------+-------------------");
                    }
                    break;
                case 0:
                    System.out.println("Program exit!!");
                    System.exit(1);
                    break;
            }
        }while(opt != 0);
    }
}
